package com.example.authenticationpoc.auth.signup.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.authenticationpoc.R
import com.example.authenticationpoc.auth.signup.model.SignUpResponseData
import com.example.authenticationpoc.auth.signup.viewmodel.SignUpViewModel
import com.example.authenticationpoc.databinding.ActivitySignUpBinding
import com.example.authenticationpoc.main.view.HomeActivity
import com.example.authenticationpoc.utility.Utility
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpViewModel: SignUpViewModel
    private var binding: ActivitySignUpBinding? = null
    private var currentUser: SignUpResponseData? = null
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding?.viewmodel = signUpViewModel
        initViews()
        setUpToolbar()
    }

    private fun initViews() {
        binding!!.btnSignup.setOnClickListener {
            signUp()
        }
    }

    private fun setUpToolbar() {
        toolbar = findViewById(R.id.toolbar_signup)
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(R.string.sign_up)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun signUp() {
        val name: String = txt_name.text.toString()
        val email: String = txt_email.text.toString()
        val password: String = txt_password.text.toString()
        val confirmPassword: String = txt_confirm_password.text.toString()
        val contactNum: String = txt_contact.text.toString()

        signUpViewModel.error.observe(this, Observer { errorMessage ->
            if (errorMessage != null) {
                Utility
                    .showSnackBar(root_signup_layout, errorMessage)

                signup_prog_bar.visibility = View.INVISIBLE
                signUpViewModel.error.postValue(null)

            }
        })
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || contactNum.isEmpty())
            Utility
                .showSnackBar(root_signup_layout, "Enter all the fields!!")
        else
            if (password != confirmPassword)
                Utility.showSnackBar(
                    root_signup_layout,
                    getString(R.string.password_confirmpassword_should_match)
                )
            else {
                signUpViewModel.user.observe(this, Observer { firebaseUser ->
                    currentUser = firebaseUser
                    if (currentUser != null) {
                        signup_prog_bar.visibility = View.INVISIBLE
                        openHomeScreen()
                    }
                })
                signUpViewModel.signUpWithEmail(name, email, password, contactNum)
                signup_prog_bar.visibility = View.VISIBLE
            }

    }

    private fun openHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
