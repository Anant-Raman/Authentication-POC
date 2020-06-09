package com.example.authenticationpoc.main.repository

import android.content.Context
import com.example.authenticationpoc.core.Constants
import com.example.authenticationpoc.core.interfaces.ResponseListener
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class LogoutUsingFirebase : HomeRepository {

    override fun logoutFromFirebase(context: Context, listener: ResponseListener<String>) {
        AuthUI.getInstance()
            .signOut(context)
            .addOnCompleteListener {
                listener.onResponse(Constants.LOGGED_OUT)
            }
    }
}
