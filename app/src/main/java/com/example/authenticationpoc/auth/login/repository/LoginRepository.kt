package com.example.authenticationpoc.auth.login.repository

import com.example.authenticationpoc.auth.login.model.AuthResponseData
import com.example.authenticationpoc.core.interfaces.ResponseListener

interface LoginRepository {

    fun loginUser(
        email: String,
        password: String,
        listener: ResponseListener<AuthResponseData>
    )
}