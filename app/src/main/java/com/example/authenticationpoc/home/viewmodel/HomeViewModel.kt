package com.example.authenticationpoc.home.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.authenticationpoc.core.Constants
import com.example.authenticationpoc.core.DataHelper
import com.example.authenticationpoc.core.interfaces.ResponseListener

class HomeViewModel : ViewModel(), ResponseListener<String> {

    val logoutResponse: MutableLiveData<String> = MutableLiveData()
    fun logout(context: Context): MutableLiveData<String> {
        DataHelper.getHomeRepository().logoutFromFirebase(context, this)
        return logoutResponse
    }

    override fun onResponse(response: String) {
        logoutResponse.postValue(response)
    }

    override fun onFailure(message: String) {
        logoutResponse.postValue(Constants.FAILED)
    }
}
