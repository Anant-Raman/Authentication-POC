package com.example.authenticationpoc.home.repository

import android.content.Context
import com.example.authenticationpoc.core.interfaces.ResponseListener

interface HomeRepository {
    fun logoutFromFirebase(context: Context, listener: ResponseListener<String>)
}