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
}