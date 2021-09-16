package com.example.kotlinmvvmbysimplified.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbysimplified.R
import com.example.kotlinmvvmbysimplified.data.db.AppDatabase
import com.example.kotlinmvvmbysimplified.data.db.entities.User
import com.example.kotlinmvvmbysimplified.data.network.MyApi
import com.example.kotlinmvvmbysimplified.data.network.NetworkConnectionInterceptor
import com.example.kotlinmvvmbysimplified.data.repository.UserRepository
import com.example.kotlinmvvmbysimplified.databinding.ActivityLoginBinding
import com.example.kotlinmvvmbysimplified.ui.home.HomeActivity
import com.example.kotlinmvvmbysimplified.util.hide
import com.example.kotlinmvvmbysimplified.util.show
import com.example.kotlinmvvmbysimplified.util.snackBar
import com.example.kotlinmvvmbysimplified.util.toast

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor) //creating MyApi and database instance for creating repository instance
        val db = AppDatabase(this)
        val repository = UserRepository(api,db)
        //for vreating viewmodel instance and passing repository as parameter,we will need viewmodelFactory

        val factory = AuthViewModelFactory(repository)


        val binding : ActivityLoginBinding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel=viewModel
        viewModel.authlistener=this

        viewModel.getLoggedInUser().observe(this, Observer {user ->
            if(user != null){
                Intent(this,HomeActivity::class.java).also {
                    it.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
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
//        root_layout.snackBar("${user.name} is Logged In")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackBar(message)

//        toast(message)
    }
}