package com.example.authenticationpoc.auth.signup.repository

import com.example.authenticationpoc.auth.signup.model.SignUpResponseData
import com.example.authenticationpoc.core.interfaces.ResponseListener

interface SignUpRepository {
    fun signUpUser(
        name: String,
        email: String,
        password: String,
        contactNum: String,
        listener: ResponseListener<SignUpResponseData>
    )
}