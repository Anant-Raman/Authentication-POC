package com.example.authenticationpoc.auth.login.repository

import android.app.Activity
import com.example.authenticationpoc.auth.login.model.AssociateAuthResponseData
import com.example.authenticationpoc.core.interfaces.ResponseListener
import com.google.firebase.auth.FirebaseAuth

class LoginUsingFirebase : LoginRepository {

    override fun loginUser(
        email: String,
        password: String,
        listener: ResponseListener<AssociateAuthResponseData>
    ) {
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(Activity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val userName = firebaseAuth.currentUser?.displayName ?: ""
                    val responseData = AssociateAuthResponseData(userName)
                    listener.onResponse(responseData)
                } else {
                    // If sign in fails, display a message to the user.
                    listener.onFailure(task.exception.toString())
                }
            }
    }
}