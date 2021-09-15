package com.example.kotlinmvvmbysimplified.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmbysimplified.data.repository.UserRepository
import com.example.kotlinmvvmbysimplified.util.Coroutines

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

        Coroutines.main {
            val response = UserRepository().userLogin(email!!,password!!)
            if(response !=null)
                authlistener?.onSuccess(response.body()?.user!!)
            else
                authlistener?.onFailure("Error Code : ${response.code()}" )



        }
    }


//    success

}