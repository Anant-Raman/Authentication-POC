package com.example.authenticationpoc.auth.signup.repository

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import com.example.authenticationpoc.R
import com.example.authenticationpoc.auth.signup.model.SignUpResponseData
import com.example.authenticationpoc.core.Constants
import com.example.authenticationpoc.core.interfaces.ResponseListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpUsingFirebase : SignUpRepository {

    private val db = Firebase.firestore

    override fun signUpUser(
        name: String,
        email: String,
        password: String,
        contactNum: String,
        listener: ResponseListener<SignUpResponseData>
    ) {
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(Activity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val userEmail = firebaseAuth.currentUser?.email ?: ""
                    val uid = firebaseAuth.currentUser?.uid ?: ""
                    val responseData = SignUpResponseData(userEmail, uid)
                    listener.onResponse(responseData)
                    addUserToFirebase(responseData, name, contactNum)
                } else {
                    // If sign in fails, display a message to the user.
                    listener.onFailure(task.exception.toString())
                }
            }
    }

    private fun addUserToFirebase(
        user: SignUpResponseData,
        name: String,
        contactNum: String
    ) {
        val usersHashMap = hashMapOf(
            Constants.CONST_NAME to name,
            Constants.CONST_CONTACT_NUM to contactNum,
            Constants.CONST_UID to user.uid,
            Constants.CONST_EMAIL to user.email
        )

        db.collection(Constants.CONST_USERS).document(user.email)
            .set(usersHashMap)
            .addOnSuccessListener {
                Log.i(
                    ContentValues.TAG, Activity().getString(R.string.data_added)
                )
            }
            .addOnFailureListener { e -> Log.i(ContentValues.TAG, "Error while adding data", e) }
    }
}
