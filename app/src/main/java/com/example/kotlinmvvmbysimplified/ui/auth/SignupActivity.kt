package com.example.kotlinmvvmbysimplified.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlinmvvmbysimplified.R
import com.example.kotlinmvvmbysimplified.data.db.entities.User
import com.example.kotlinmvvmbysimplified.databinding.ActivityLoginBinding
import com.example.kotlinmvvmbysimplified.databinding.ActivitySignupBinding
import com.example.kotlinmvvmbysimplified.ui.home.HomeActivity
import com.example.kotlinmvvmbysimplified.util.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.progress_bar
import kotlinx.android.synthetic.main.activity_login.root_layout
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(),KodeinAware {
    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()
    private lateinit var binding : ActivitySignupBinding
    private lateinit var viewModel : AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding  = DataBindingUtil.setContentView(this,R.layout.activity_signup)
         viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)
//        binding.viewmodel=viewModel
//        viewModel.authlistener=this

        viewModel.getLoggedInUser().observe(this, Observer {user ->
            if(user != null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

        binding.buttonSignUp.setOnClickListener {
            userSignUp()
        }
    }

    private fun userSignUp() {
        val name = binding.editTextName.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        val confPassword = binding.editTextPasswordConfirm.text.toString().trim()

        lifecycleScope.launch {
            try{
         val  authResponse =  viewModel.userSignUp(name,email,password,confPassword)
                if(authResponse.user!=null){
                    viewModel.saveLoggedInUser(authResponse.user) //save user to local database

                }else{
                    binding.root.snackBar(authResponse.message!!)
                }

        } catch (e: ApiException) {
            e.printStackTrace()

        }catch (e: NoInternetException){
            e.printStackTrace()

        }
        }
    }


}