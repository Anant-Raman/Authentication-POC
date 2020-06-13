package com.example.authenticationpoc.home.repository

import android.content.Context
import com.example.authenticationpoc.core.Constants
import com.example.authenticationpoc.core.interfaces.ResponseListener
import com.firebase.ui.auth.AuthUI

class LogoutUsingFirebase : HomeRepository {

    override fun logoutFromFirebase(context: Context, listener: ResponseListener<String>) {
        AuthUI.getInstance()
            .signOut(context)
            .addOnCompleteListener {
                listener.onResponse(Constants.LOGGED_OUT)
            }
    }
}
