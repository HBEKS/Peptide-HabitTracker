package com.ubayadev.peptideuts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    var id: String?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "description")
    var description: String?,
    @ColumnInfo(name = "goal")
    var goal: Int?,
    @ColumnInfo(name = "unit")
    var unit: String?,
    @ColumnInfo(name = "progress")
    var progress: Int?,
    @ColumnInfo(name = "iconName")
    var iconName: String?
)