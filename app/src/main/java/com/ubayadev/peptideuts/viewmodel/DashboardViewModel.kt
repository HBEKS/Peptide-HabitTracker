package com.ubayadev.peptideuts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubayadev.peptideuts.model.Habit
import com.ubayadev.peptideuts.util.FileHelper

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val loadingLD = MutableLiveData<Boolean>()
    val completedEvent = MutableLiveData<String?>()

    private val fileHelper = FileHelper(getApplication())

    fun refresh() {
        loadingLD.value = true

        val jsonString = fileHelper.readFromFile()

        if (jsonString.isEmpty()) {
            habitsLD.value = arrayListOf()
        } else {
            try {
                val sType = object : TypeToken<ArrayList<Habit>>() {}.type
                val result = Gson().fromJson<ArrayList<Habit>>(jsonString, sType)
                habitsLD.value = result ?: arrayListOf()
            } catch (e: Exception) {
                e.printStackTrace()
                habitsLD.value = arrayListOf()
            }
        }

        loadingLD.value = false
    }

    fun addHabit(habit: Habit) {
        val currentList = habitsLD.value ?: arrayListOf()
        habit.id = System.currentTimeMillis().toString()
        habit.progress = 0
        currentList.add(habit)
        habitsLD.value = currentList
        saveToFile(currentList)
    }

    fun updateProgress(habit: Habit, delta: Int) {
        val currentList = habitsLD.value ?: return
        val index = currentList.indexOfFirst { it.id == habit.id }
        if (index == -1) return

        val target = currentList[index]
        val oldProgress = target.progress ?: 0
        val goal = target.goal ?: 1
        val newProgress = oldProgress + delta

        var finalProgress = newProgress
        if (finalProgress < 0) finalProgress = 0
        if (finalProgress > goal) finalProgress = goal
        target.progress = finalProgress

        // Trigger snackbar saat habit baru saja menjadi tuntas
        if (oldProgress < goal && finalProgress >= goal) {
            completedEvent.value = target.name ?: "Habit"
        }

        habitsLD.value = currentList
        saveToFile(currentList)
    }

    fun clearCompletedEvent() {
        completedEvent.value = null
    }

    private fun saveToFile(list: ArrayList<Habit>) {
        val jsonString = Gson().toJson(list)
        fileHelper.writeToFile(jsonString)
    }
}