package com.example.kotlinmvvmbysimplified.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmbysimplified.data.network.NetworkConnectionInterceptor
import com.example.kotlinmvvmbysimplified.data.repository.UserRepository
import com.example.kotlinmvvmbysimplified.util.ApiException
import com.example.kotlinmvvmbysimplified.util.Coroutines
import com.example.kotlinmvvmbysimplified.util.NoInternetException

class AuthViewModel (
    private val repository: UserRepository
        ): ViewModel() {

    var email: String? = null
    var password: String? = null
    var authlistener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        authlistener?.onStarted()

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authlistener?.onFailure("Invalid Email or Password")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authlistener?.onSuccess(it)
                    repository.saveUser(it) //save user to local database
                    return@main
                }
                authlistener?.onFailure(authResponse.message!!)

            } catch (e: ApiException) {
                authlistener?.onFailure(e.message!!)

            }catch (e:NoInternetException){
                authlistener?.onFailure(e.message!!)

            }
        }
    }


//    success

}