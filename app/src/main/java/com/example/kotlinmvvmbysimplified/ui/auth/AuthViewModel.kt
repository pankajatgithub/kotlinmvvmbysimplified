package com.example.kotlinmvvmbysimplified.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmbysimplified.data.db.entities.User
import com.example.kotlinmvvmbysimplified.data.network.NetworkConnectionInterceptor
import com.example.kotlinmvvmbysimplified.data.repository.UserRepository
import com.example.kotlinmvvmbysimplified.ui.home.HomeActivity
import com.example.kotlinmvvmbysimplified.util.ApiException
import com.example.kotlinmvvmbysimplified.util.Coroutines
import com.example.kotlinmvvmbysimplified.util.NoInternetException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthViewModel (
    private val repository: UserRepository
        ): ViewModel() {

//    var name:String? = null
//    var email: String? = null
//    var password: String? = null
//    var passwordConfirm:String? = null
//
//    var authlistener: AuthListener? = null


    fun getLoggedInUser() = repository.getUser()

    suspend fun userLogin(
        email : String,
        password : String
    ) = withContext(Dispatchers.IO){   //Network call will be always made on Dispatchers.IO ,i.e IO thread,not in main thread
        repository.userLogin(email, password)
    }

    suspend fun userSignUp(
        name:String,
        email: String,
        password: String,
        password_confirmation:String
    ) = withContext(Dispatchers.IO){
        repository.userSignUp(name, email, password, password_confirmation)
    }

    suspend fun saveLoggedInUser(user: User) = repository.saveUser(user)

//    fun onLoginButtonClick(view: View) {
//        authlistener?.onStarted()
//
//        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
//            authlistener?.onFailure("Invalid Email or Password")
//            return
//        }
//
//        Coroutines.main {
//            try {
//                val authResponse = repository.userLogin(email!!, password!!)
//                authResponse.user?.let {
//                    authlistener?.onSuccess(it)
//                    repository.saveUser(it) //save user to local database
//                    return@main
//                }
//                authlistener?.onFailure(authResponse.message!!)
//
//            } catch (e: ApiException) {
//                authlistener?.onFailure(e.message!!)
//
//            }catch (e:NoInternetException){
//                authlistener?.onFailure(e.message!!)
//
//            }
//        }
//    }
//
//    fun onSignIn(view : View){
//        Intent(view.context,LoginActivity::class.java).also {
//            view.context.startActivity(it)
//        }
//    }
//
//    fun onSignUP(view : View){
//        Intent(view.context ,SignupActivity::class.java).also {
//            view.context.startActivity(it)
//        }
//    }
//    fun onSignUpButtonClick(view: View) {
//        authlistener?.onStarted()
//
//        if(name.isNullOrEmpty()){
//            authlistener?.onFailure("Name is required")
//            return
//        }
//        if(email.isNullOrEmpty()){
//            authlistener?.onFailure("email is required")
//            return
//        }
//        if(password.isNullOrEmpty()){
//            authlistener?.onFailure("Please enter a password")
//            return
//        }
//        if(passwordConfirm != password){
//            authlistener?.onFailure("password didn't match")
//            return
//        }
//
//
//
//        Coroutines.main {
//            try {
//                val authResponse = repository.userSignUp(name!!,email!!, password!!,passwordConfirm!!)
//                authResponse.user?.let {
//                    authlistener?.onSuccess(it)
//                    repository.saveUser(it) //save user to local database
//                    return@main
//                }
//                authlistener?.onFailure(authResponse.message!!)
//
//            } catch (e: ApiException) {
//                authlistener?.onFailure(e.message!!)
//
//            }catch (e:NoInternetException){
//                authlistener?.onFailure(e.message!!)
//
//            }
//        }
//    }


//    success

}