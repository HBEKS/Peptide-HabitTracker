package com.ubayadev.peptideuts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubayadev.peptideuts.model.Habit

class CreateHabitViewModel : ViewModel() {

    val habitCreated = MutableLiveData<Habit>()

    val nameError = MutableLiveData<String?>()
    val descriptionError = MutableLiveData<String?>()
    val goalError = MutableLiveData<String?>()
    val unitError = MutableLiveData<String?>()

    fun createHabit(
        name: String,
        description: String,
        goalStr: String,
        unit: String,
        iconName: String
    ) {
        nameError.value = null
        descriptionError.value = null
        goalError.value = null
        unitError.value = null

        var hasError = false
        if (name.isEmpty()) {
            nameError.value = "Nama habit tidak boleh kosong"
            hasError = true
        }
        if (description.isEmpty()) {
            descriptionError.value = "Deskripsi tidak boleh kosong"
            hasError = true
        }
        if (unit.isEmpty()) {
            unitError.value = "Unit tidak boleh kosong"
            hasError = true
        }

        val goal = goalStr.toIntOrNull()
        if (goalStr.isEmpty()) {
            goalError.value = "Goal tidak boleh kosong"
            hasError = true
        } else if (goal == null || goal <= 0) {
            goalError.value = "Goal harus berupa angka > 0"
            hasError = true
        }

        if (hasError) return

        val newHabit = Habit(
            id = "",
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