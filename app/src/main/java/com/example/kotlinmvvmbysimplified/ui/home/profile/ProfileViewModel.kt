package com.example.kotlinmvvmbysimplified.ui.home.profile

import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmbysimplified.data.repository.UserRepository

class ProfileViewModel (
    repository: UserRepository
        ): ViewModel() {
    val user = repository.getUser()
}