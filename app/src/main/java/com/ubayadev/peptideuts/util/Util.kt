package com.ubayadev.peptideuts.util

import android.content.Context
import com.ubayadev.peptideuts.model.HabitDatabase

fun buildDb(context: Context): HabitDatabase {
    return HabitDatabase(context)
}