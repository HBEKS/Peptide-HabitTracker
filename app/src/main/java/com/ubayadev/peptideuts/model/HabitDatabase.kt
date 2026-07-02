package com.ubayadev.peptideuts.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false) //belum masukin habit jadinya nanti ke version 2
abstract class HabitDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DB_NAME = "habitdb"

        @Volatile
        private var instance: HabitDatabase? = null
        private val LOCK = Any()

        fun buildDatabase(context: Context): HabitDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                HabitDatabase::class.java,
                DB_NAME
            ).build()

        operator fun invoke(context: Context): HabitDatabase = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
    }
}