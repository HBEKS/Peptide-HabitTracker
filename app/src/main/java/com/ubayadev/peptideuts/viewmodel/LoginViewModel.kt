package com.ubayadev.peptideuts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val loginSuccess = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun checkLogin(usernameInput: String, passwordInput: String) {
        if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
            errorMessage.value = "Username dan Password tidak boleh kosong!"
            return
        }

        // Logika Hardcode sesuai permintaan soal
        if (usernameInput == "student" && passwordInput == "123") {
            loginSuccess.value = true
        } else {
            loginSuccess.value = false
            errorMessage.value = "Username atau Password salah!"
        }
    }
}