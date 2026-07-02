package com.ubayadev.peptideuts.util

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {
    private val pref: SharedPreferences = context.getSharedPreferences("UserSession", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = pref.edit()

    companion object {
        private const val KEY_IS_LOGIN = "isLogin"
        private const val KEY_USERNAME = "username"
    }

    // Fungsi untuk menyimpan session saat login berhasil
    fun saveSession(username: String) {
        editor.putBoolean(KEY_IS_LOGIN, true)
        editor.putString(KEY_USERNAME, username)
        editor.apply() // Menggunakan apply agar berjalan secara asynchronous (background)
    }

    // Fungsi untuk mengecek apakah user sudah login sebelumnya
    fun isLoggedIn(): Boolean {
        return pref.getBoolean(KEY_IS_LOGIN, false)
    }

    // Fungsi untuk mengambil username yang sedang login (jika nanti dibutuhkan di Dashboard)
    fun getUsername(): String? {
        return pref.getString(KEY_USERNAME, null)
    }

    // Fungsi untuk logout (menghapus session)
    fun clearSession() {
        editor.clear()
        editor.apply()
    }
}