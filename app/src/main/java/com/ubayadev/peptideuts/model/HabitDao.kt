package com.ubayadev.peptideuts.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabit(vararg habits: Habit)

    @Query("SELECT * FROM habit")
    fun selectAllHabit(): List<Habit>

    @Query("SELECT * FROM habit WHERE id = :id")
    fun selectHabit(id: String): Habit?

    @Update
    fun updateHabit(habitItem: Habit)

    @Delete
    fun deleteHabit(habitItem: Habit)

    @Query("SELECT * FROM habit ORDER BY name ASC")
    fun selectAllHabitOrdered(): List<Habit>

    @Query("SELECT COUNT(*) FROM habit")
    fun countAllHabit(): Int

    @Query("SELECT COUNT(*) FROM habit WHERE progress >= goal")
    fun countCompletedHabit(): Int

    @Query("SELECT * FROM habit WHERE progress < goal")
    fun selectInProgressHabit(): List<Habit>

    @Query("SELECT * FROM habit WHERE progress >= goal")
    fun selectCompletedHabit(): List<Habit>

    @Query("SELECT * FROM habit WHERE name LIKE '%' || :keyword || '%'")
    fun searchHabitByName(keyword: String): List<Habit>

    @Query("DELETE FROM habit WHERE progress >= goal")
    fun deleteCompletedHabit()
}