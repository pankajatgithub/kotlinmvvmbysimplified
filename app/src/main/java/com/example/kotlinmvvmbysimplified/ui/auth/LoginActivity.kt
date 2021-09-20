package com.example.kotlinmvvmbysimplified.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.example.kotlinmvvmbysimplified.R
import com.example.kotlinmvvmbysimplified.data.db.AppDatabase
import com.example.kotlinmvvmbysimplified.data.db.entities.User
import com.example.kotlinmvvmbysimplified.data.network.MyApi
import com.example.kotlinmvvmbysimplified.data.network.NetworkConnectionInterceptor
import com.example.kotlinmvvmbysimplified.data.repository.UserRepository
import com.example.kotlinmvvmbysimplified.databinding.ActivityLoginBinding
import com.example.kotlinmvvmbysimplified.ui.home.HomeActivity
import com.example.kotlinmvvmbysimplified.util.*

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(),KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()
    private lateinit var binding : ActivityLoginBinding
    private lateinit var viewModel : AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)

//        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
//        val api = MyApi(networkConnectionInterceptor) //creating MyApi and database instance for creating repository instance
//        val db = AppDatabase(this)
//        val repository = UserRepository(api,db)
//        //for vreating viewmodel instance and passing repository as parameter,we will need viewmodelFactory
//
//        val factory = AuthViewModelFactory(repository) //all these objects will be injected using kodein


         binding =DataBindingUtil.setContentView(this,R.layout.activity_login)
         viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)

        viewModel.getLoggedInUser().observe(this, Observer {user ->
            if(user != null){
                Intent(this,HomeActivity::class.java).also {
                    it.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

            binding.buttonSignIn.setOnClickListener {
                logininUser()
            }
    }

    private fun logininUser() {
        val email =binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()

//        inside activity,we can use coroutine using lifecyclescope,but inside fragment we will use viewLifecyclescope
//        to launch coroutine

        lifecycleScope.launch {
            try {
            val authResponse = viewModel.userLogin(email!!, password!!)
               if(authResponse.user!=null){
                    viewModel.saveLoggedInUser(authResponse.user) //save user to local database

                }else{
                    binding.rootLayout.snackBar(authResponse.message!!)
                }

            } catch (e: ApiException) {
                e.printStackTrace()

            }catch (e:NoInternetException){
                e.printStackTrace()

            }
        }
    }


}