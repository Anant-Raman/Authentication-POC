package com.example.authenticationpoc.auth.signup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.authenticationpoc.auth.signup.model.SignUpResponseData
import com.example.authenticationpoc.core.DataHelper
import com.example.authenticationpoc.core.interfaces.ResponseListener

class SignUpViewModel : ViewModel(), ResponseListener<SignUpResponseData> {

    var user: MutableLiveData<SignUpResponseData?> = MutableLiveData()
    var error = MutableLiveData<String>()

    fun signUpWithEmail(
        name: String,
        email: String,
        password: String,
        contactNum: String
    ): LiveData<SignUpResponseData?> {
        DataHelper.getSignUpRepository().signUpUser(name, email, password, contactNum, this)
        return user
    }

    override fun onResponse(response: SignUpResponseData) {
        user.postValue(response)
    }

    override fun onFailure(message: String) {
        user.postValue(null)
        error.value = message.substring(message.indexOf(':') + 1)
    }
}