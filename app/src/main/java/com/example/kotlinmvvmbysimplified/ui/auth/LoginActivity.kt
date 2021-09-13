package com.example.kotlinmvvmbysimplified.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlinmvvmbysimplified.R
import com.example.kotlinmvvmbysimplified.databinding.ActivityLoginBinding
import com.example.kotlinmvvmbysimplified.util.toast

class LoginActivity : AppCompatActivity(),AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val binding : ActivityLoginBinding=DataBindingUtil.setContentView(this,R.layout.activity_login)
    }

    override fun onStarted() {
  toast("Login Started")
    }

    override fun onSuccess() {
        toast("Login Success")
    }

    override fun onFailure(message: String) {
        toast(message)
    }
}