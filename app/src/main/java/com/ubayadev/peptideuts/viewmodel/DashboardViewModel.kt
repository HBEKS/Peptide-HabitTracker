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

class DashboardViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.IO

    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val loadingLD = MutableLiveData<Boolean>()
    val completedEvent = MutableLiveData<String?>()

    fun refresh() {
        loadingLD.value = true

        launch {
            val db = buildDb(getApplication())
            val list = ArrayList(db.habitDao().selectAllHabit())
            habitsLD.postValue(list)
            loadingLD.postValue(false)
        }
    }

    fun addHabit(habit: Habit) {
        habit.id = System.currentTimeMillis().toString()
        habit.progress = 0

        launch {
            val db = buildDb(getApplication())
            db.habitDao().insertHabit(habit)
            val list = ArrayList(db.habitDao().selectAllHabit())
            habitsLD.postValue(list)
        }
    }

    fun updateProgress(habit: Habit, delta: Int) {
        val oldProgress = habit.progress ?: 0
        val goal = habit.goal ?: 1
        var newProgress = oldProgress + delta

        if (newProgress < 0) newProgress = 0
        if (newProgress > goal) newProgress = goal
        habit.progress = newProgress

        launch {
            val db = buildDb(getApplication())
            db.habitDao().updateHabit(habit)
            val list = ArrayList(db.habitDao().selectAllHabit())
            habitsLD.postValue(list)

            // Trigger snackbar saat habit baru saja menjadi tuntas
            if (oldProgress < goal && newProgress >= goal) {
                completedEvent.postValue(habit.name ?: "Habit")
            }
        }
    }

    fun clearCompletedEvent() {
        completedEvent.value = null
    }
}