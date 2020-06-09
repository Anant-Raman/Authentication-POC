package com.example.authenticationpoc.main.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.authenticationpoc.R
import com.example.authenticationpoc.auth.login.view.LoginActivity
import com.example.authenticationpoc.core.Constants
import com.example.authenticationpoc.main.viewmodel.HomeViewModel
import com.example.authenticationpoc.utility.Utility

class HomeActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        setUpToolbar()
    }

    private fun setUpToolbar() {
        toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(getString(R.string.home))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                logout()
            }
        }
        return true
    }

    private fun logout() {
        homeViewModel.logoutResponse.observe(this, Observer { response ->
            if (response.equals(Constants.LOGGED_OUT)) {
                Utility.clearSharedPref(this)
                openLogin()
            }
        })
        homeViewModel.logout(this)
    }

    private fun openLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
