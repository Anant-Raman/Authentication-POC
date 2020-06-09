package com.example.authenticationpoc.auth.login.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.authenticationpoc.R
import com.example.authenticationpoc.auth.login.model.AssociateAuthResponseData
import com.example.authenticationpoc.auth.login.viewmodel.LoginViewModel
import com.example.authenticationpoc.auth.signup.view.SignUpActivity
import com.example.authenticationpoc.core.Constants
import com.example.authenticationpoc.main.view.HomeActivity
import com.example.authenticationpoc.utility.Utility
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private var currentUser: AssociateAuthResponseData? = null
    private lateinit var context: Context
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        context = this
        initViews()
        setUpToolbar()
    }

    private fun setUpToolbar() {
        toolbar = findViewById(R.id.toolbar_login)
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(getString(R.string.authentication_poc_app))
    }


    private fun initViews() {
        btn_login.setOnClickListener {
            loginWithEmail()
        }

        txt_signup.setOnClickListener {
            openSignUpScreen()
        }
    }

    private fun loginWithEmail() {
        val email = txt_email.text
        val password = txt_password.text

        loginViewModel.error.observe(this, Observer { errorMessage ->
            if (errorMessage != null) {
                Utility
                    .showSnackBar(root_layout, errorMessage)
                login_prog_bar.visibility = View.INVISIBLE
                loginViewModel.error.postValue(null)
            }
        })

        if (email.isNullOrEmpty() || password.isNullOrEmpty())
            Utility
                .showSnackBar(root_layout, getString(R.string.empty_email_or_password))
        else {
            loginViewModel.user.observe(this, Observer { user ->
                currentUser = user
                if (currentUser != null) {
                    Utility.storeStringSharedPref(Constants.USER_ID, email.toString(), context)
                    login_prog_bar.visibility = View.INVISIBLE
                    openHomeScreen()
                }
            })
            loginViewModel.login(
                email = email.toString(),
                password = password.toString()
            )
            login_prog_bar.visibility = View.VISIBLE
        }
    }

    private fun openHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun openSignUpScreen() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}