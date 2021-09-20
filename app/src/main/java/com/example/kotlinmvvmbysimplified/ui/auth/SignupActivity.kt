package com.example.kotlinmvvmbysimplified.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbysimplified.R
import com.example.kotlinmvvmbysimplified.data.db.entities.User
import com.example.kotlinmvvmbysimplified.databinding.ActivityLoginBinding
import com.example.kotlinmvvmbysimplified.databinding.ActivitySignupBinding
import com.example.kotlinmvvmbysimplified.ui.home.HomeActivity
import com.example.kotlinmvvmbysimplified.util.hide
import com.example.kotlinmvvmbysimplified.util.show
import com.example.kotlinmvvmbysimplified.util.snackBar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.progress_bar
import kotlinx.android.synthetic.main.activity_login.root_layout
import kotlinx.android.synthetic.main.activity_signup.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(),AuthListener,KodeinAware {
    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivitySignupBinding = DataBindingUtil.setContentView(this,R.layout.activity_signup)
        val viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)
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
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
//        signup_root_layout.snackBar("${user.name} is Logged In")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        signup_root_layout.snackBar(message)

//        toast(message)
    }
}