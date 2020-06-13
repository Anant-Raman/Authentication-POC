package com.example.authenticationpoc.auth.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.authenticationpoc.auth.login.model.AuthResponseData
import com.example.authenticationpoc.core.DataHelper
import com.example.authenticationpoc.core.interfaces.ResponseListener

class LoginViewModel : ViewModel(),
    ResponseListener<AuthResponseData> {

    var user: MutableLiveData<AuthResponseData?> = MutableLiveData()
    var error = MutableLiveData<String>()

    fun login(email: String, password: String): LiveData<AuthResponseData?> {
        DataHelper.getLoginRepository().loginUser(email, password, this)
        return user
    }

    override fun onResponse(response: AuthResponseData) {
        user.postValue(response)
    }

    override fun onFailure(message: String) {
        user.postValue(null)
        error.value = message.substring(message.indexOf(':') + 1)
    }
}
