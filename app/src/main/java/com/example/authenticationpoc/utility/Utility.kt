package com.example.authenticationpoc.utility

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.authenticationpoc.R
import com.example.authenticationpoc.core.Constants
import com.google.android.material.snackbar.Snackbar


object Utility {

    fun showSnackBar(context: ConstraintLayout, message: String) {
        Snackbar.make(context, message, Snackbar.LENGTH_LONG)
            .setAction(Constants.CONST_ACTION, null)
            .show()
    }

    fun storeStringSharedPref(key: String?, value: String, context: Context) {
        val sharedPreferences = context.applicationContext.getSharedPreferences(
            context.applicationContext.getString(
                R.string.preference_file_key
            ), Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getStringSharedPref(key: String?, context: Context): String? {
        val sharedPreferences = context.applicationContext.getSharedPreferences(
            context.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )
        return sharedPreferences.getString(key, null)
    }

    fun clearSharedPref(context: Context) {
        val sharedPreferences = context.applicationContext.getSharedPreferences(
            context.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}