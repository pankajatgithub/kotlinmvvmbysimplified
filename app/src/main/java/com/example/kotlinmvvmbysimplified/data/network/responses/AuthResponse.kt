package com.example.kotlinmvvmbysimplified.data.network.responses

import com.example.kotlinmvvmwithlaravel.data.db.entities.User

data class AuthResponse (
    val user:User?,
    val message:String?
)