package com.example.authenticationpoc.core.interfaces

interface ResponseListener<T> {

    fun onResponse(response: T)

    fun onFailure(message: String)

}