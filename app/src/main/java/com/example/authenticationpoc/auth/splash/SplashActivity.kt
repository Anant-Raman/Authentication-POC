package com.example.authenticationpoc.auth.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.authenticationpoc.R
import com.example.authenticationpoc.auth.login.view.LoginActivity
import com.example.authenticationpoc.core.Constants
import com.example.authenticationpoc.main.view.HomeActivity
import com.example.authenticationpoc.utility.Utility

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val isUserLoggedIn = isUserLoggedIn()
        if (isUserLoggedIn) {
            launchHomeScreen()
        } else {
            launchLoginScreen()
        }
    }

    private fun isUserLoggedIn(): Boolean {
        val userId = Utility.getStringSharedPref(Constants.USER_ID, this)
        return userId != null && userId.isNotEmpty()
    }

    private fun launchHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun launchLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
