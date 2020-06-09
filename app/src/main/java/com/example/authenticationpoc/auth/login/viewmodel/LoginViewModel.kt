package com.example.authenticationpoc.auth.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.authenticationpoc.auth.login.model.AssociateAuthResponseData
import com.example.authenticationpoc.core.DataHelper
import com.example.authenticationpoc.core.interfaces.ResponseListener

class LoginViewModel : ViewModel(),
    ResponseListener<AssociateAuthResponseData> {

    var user: MutableLiveData<AssociateAuthResponseData?> = MutableLiveData()
    var error = MutableLiveData<String>()

    fun login(email: String, password: String): LiveData<AssociateAuthResponseData?> {
        DataHelper.getLoginRepository().loginUser(email, password, this)
        return user
    }

    override fun onResponse(response: AssociateAuthResponseData) {
        user.postValue(response)
    }

    override fun onFailure(message: String) {
        user.postValue(null)
        error.value = message.substring(message.indexOf(':') + 1)
    }
}
