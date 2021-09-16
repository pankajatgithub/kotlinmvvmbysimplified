package com.example.kotlinmvvmbysimplified.data.repository


import com.example.kotlinmvvmbysimplified.data.db.AppDatabase
import com.example.kotlinmvvmbysimplified.data.db.entities.User
import com.example.kotlinmvvmbysimplified.data.network.MyApi
import com.example.kotlinmvvmbysimplified.data.network.SafeApiRequest
import com.example.kotlinmvvmbysimplified.data.network.responses.AuthResponse


import retrofit2.Response
//it is not good practice to create class instance inside any class,so we use constructor injection here
class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {

        return apiRequest { api.userlogin(email, password) }
    }
    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}