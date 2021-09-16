package com.example.kotlinmvvmbysimplified.ui.auth

import androidx.lifecycle.LiveData
import com.example.kotlinmvvmbysimplified.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message:String)
}