package com.example.kotlinmvvmbysimplified.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbysimplified.data.repository.QuotesRepository
import com.example.kotlinmvvmbysimplified.data.repository.UserRepository

class QuotesViewModelFactory(
    private val repository: QuotesRepository

) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }
}