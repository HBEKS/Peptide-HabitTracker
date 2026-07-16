package com.ubayadev.peptideuts.util

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubayadev.peptideuts.model.HabitDatabase

fun buildDb(context: Context): HabitDatabase {
    return HabitDatabase(context)
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE user ADD COLUMN is_login INTEGER NOT NULL DEFAULT 0")
    }
}
