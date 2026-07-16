package com.ubayadev.peptideuts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubayadev.peptideuts.model.Habit
import com.ubayadev.peptideuts.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class EditHabitViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.IO

    val habitLD = MutableLiveData<Habit>()

    fun loadHabit(id: String) {
        launch {
            val db = buildDb(getApplication())
            val habit = db.habitDao().selectHabit(id)
            if (habit != null) {
                habitLD.postValue(habit)
            }
        }
    }

    fun updateHabit(habit: Habit) {
        launch {
            val db = buildDb(getApplication())
            db.habitDao().updateHabit(habit)
        }
    }

    fun deleteHabit(habit: Habit) {
        launch {
            val db = buildDb(getApplication())
            db.habitDao().deleteHabit(habit)
        }
    }
}