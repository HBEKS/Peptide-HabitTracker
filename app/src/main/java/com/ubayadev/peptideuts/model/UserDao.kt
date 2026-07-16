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

    @Query("SELECT * FROM user WHERE username = :username")
    fun selectUserByUsername(username: String): User?

    @Query("SELECT * FROM user WHERE is_login = 1 LIMIT 1")
    fun selectLoggedInUser(): User?

    @Query("UPDATE user SET is_login = 1 WHERE username = :username")
    fun markLogin(username: String)

    @Query("UPDATE user SET is_login = 0")
    fun clearLoginSession()
}