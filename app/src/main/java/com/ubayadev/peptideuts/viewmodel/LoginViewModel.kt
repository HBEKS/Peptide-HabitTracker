package com.ubayadev.peptideuts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val loginSuccess = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    val usernameError = MutableLiveData<String?>()
    val passwordError = MutableLiveData<String?>()

    fun checkLogin(usernameInput: String, passwordInput: String) {
        usernameError.value = null
        passwordError.value = null

        var hasError = false
        if (usernameInput.isEmpty()) {
            usernameError.value = "Username tidak boleh kosong"
            hasError = true
        }
        if (passwordInput.isEmpty()) {
            passwordError.value = "Password tidak boleh kosong"
            hasError = true
        }
        if (hasError) return

        // Logika Hardcode sesuai permintaan soal
        if (usernameInput == "student" && passwordInput == "123") {
            loginSuccess.value = true
        } else {
            errorMessage.value = "Username atau Password salah!"
        }
    }
}