package com.example.kotlinmvvmbysimplified.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    var email:String? = null
    var password:String? = null
    var authlistener:AuthListener? = null

    fun onLoginButtonClick(view : View){
        authlistener?.onStarted()

        if(email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authlistener?.onFailure("Invalid Email or Password")
             return
        }
        authlistener?.onSuccess()
    }


//    success

}