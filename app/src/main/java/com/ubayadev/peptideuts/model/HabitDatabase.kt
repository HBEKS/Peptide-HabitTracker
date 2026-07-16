package com.ubayadev.peptideuts.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubayadev.peptideuts.util.MIGRATION_1_2

@Database(entities = [User::class, Habit::class], version = 2, exportSchema = false)
abstract class HabitDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun habitDao(): HabitDao

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
            ).addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .build()

        operator fun invoke(context: Context): HabitDatabase = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
    }
}