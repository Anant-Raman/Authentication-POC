package com.example.authenticationpoc.auth.login.repository

import com.example.authenticationpoc.auth.login.model.AssociateAuthResponseData
import com.example.authenticationpoc.core.interfaces.ResponseListener

interface LoginRepository {

    fun loginUser(
        email: String,
        password: String,
        listener: ResponseListener<AssociateAuthResponseData>
    )
}