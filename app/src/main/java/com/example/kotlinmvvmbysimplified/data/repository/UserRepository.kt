package com.example.kotlinmvvmbysimplified.data.repository


import com.example.kotlinmvvmbysimplified.data.network.MyApi
import com.example.kotlinmvvmbysimplified.data.network.SafeApiRequest
import com.example.kotlinmvvmbysimplified.data.network.responses.AuthResponse

import retrofit2.Response

class UserRepository : SafeApiRequest(){

   suspend fun userLogin(
        email: String,
        password: String
    ): AuthResponse {


       return apiRequest { MyApi().userlogin(email, password) }
   }
}