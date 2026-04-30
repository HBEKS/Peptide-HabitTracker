package com.ubayadev.peptideuts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubayadev.peptideuts.model.Habit

class CreateHabitViewModel : ViewModel() {

    val habitCreated = MutableLiveData<Habit>()
    val errorMessage = MutableLiveData<String>()

    fun createHabit(
        name: String,
        description: String,
        goalStr: String,
        unit: String,
        iconName: String
    ) {
        if (name.isEmpty() || description.isEmpty() || goalStr.isEmpty() || unit.isEmpty()) {
            errorMessage.value = "Semua field harus diisi!"
            return
        }

        val goal = goalStr.toIntOrNull()
        if (goal == null || goal <= 0) {
            errorMessage.value = "Goal harus berupa angka lebih dari 0!"
            return
        }

        val newHabit = Habit(
            id = null,
            name = name,
            description = description,
            goal = goal,
            unit = unit,
            progress = 0,
            iconName = iconName
        )

        habitCreated.value = newHabit
    }
}