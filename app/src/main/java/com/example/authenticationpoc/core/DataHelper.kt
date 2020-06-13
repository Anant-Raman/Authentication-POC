package com.example.authenticationpoc.core

import com.example.authenticationpoc.auth.login.repository.LoginRepository
import com.example.authenticationpoc.auth.login.repository.LoginUsingFirebase
import com.example.authenticationpoc.auth.signup.repository.SignUpRepository
import com.example.authenticationpoc.auth.signup.repository.SignUpUsingFirebase
import com.example.authenticationpoc.home.repository.HomeRepository
import com.example.authenticationpoc.home.repository.LogoutUsingFirebase

object DataHelper {

    private val loginRepository: LoginRepository = LoginUsingFirebase()
    private val signUpRepository: SignUpRepository = SignUpUsingFirebase()
    private val homeRepository: HomeRepository = LogoutUsingFirebase()

    fun getLoginRepository(): LoginRepository {
        return loginRepository
    }

    fun getSignUpRepository(): SignUpRepository {
        return signUpRepository
    }

    fun getHomeRepository(): HomeRepository {
        return homeRepository
    }
}