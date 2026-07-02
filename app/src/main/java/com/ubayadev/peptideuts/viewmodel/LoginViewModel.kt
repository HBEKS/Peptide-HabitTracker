package com.ubayadev.peptideuts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubayadev.peptideuts.model.User
import com.ubayadev.peptideuts.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application) : AndroidViewModel(application), CoroutineScope{
    private val job = Job()
    val loginSuccess = MutableLiveData<Boolean>()
    fun loginUser(username: String, password: String){
        launch {
            val db = buildDb(getApplication())
            val user = db.userDao().login(username, password)
            if (user == null){
                val newUser = User(username = username, password = password)
                db.userDao().insertUser(newUser)
                loginSuccess.postValue(user != null)
            } else {
                loginSuccess.postValue(user != null)
            }

        }
    }

    override val coroutineContext: CoroutineContext get() = job + Dispatchers.IO

    /*
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
    }*/
}