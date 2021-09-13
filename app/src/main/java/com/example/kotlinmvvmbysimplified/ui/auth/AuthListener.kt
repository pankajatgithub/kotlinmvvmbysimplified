package com.example.kotlinmvvmbysimplified.ui.auth

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message:String)
}