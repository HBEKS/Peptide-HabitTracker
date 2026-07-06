package com.ubayadev.peptideuts.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg users: User)
    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    fun login(username: String, password: String): User?
    @Query("SELECT COUNT(*) FROM user")
    fun countUser(): Int
    @Query("SELECT * FROM user WHERE is_login = 1 LIMIT 1")
    fun checkActiveSession(): User?
    @Query("UPDATE user SET is_login = :status WHERE id = :userId")
    fun updateSessionStatus(userId: Int, status: Boolean)
}