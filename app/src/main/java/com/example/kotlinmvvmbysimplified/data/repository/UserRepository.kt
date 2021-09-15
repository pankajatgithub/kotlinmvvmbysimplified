package com.example.kotlinmvvmbysimplified.data.repository


import com.example.kotlinmvvmbysimplified.data.network.MyApi
import com.example.kotlinmvvmbysimplified.data.network.responses.AuthResponse

import retrofit2.Response

class UserRepository {

   suspend fun userLogin(
        email: String,
        password: String
    ): Response<AuthResponse> {

       return MyApi().userlogin(email, password)
   }
}