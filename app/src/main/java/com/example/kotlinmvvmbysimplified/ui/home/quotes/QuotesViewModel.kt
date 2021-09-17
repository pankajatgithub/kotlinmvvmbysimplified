package com.example.kotlinmvvmbysimplified.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmbysimplified.data.repository.QuotesRepository
import com.example.kotlinmvvmbysimplified.util.lazyDeferred

class QuotesViewModel (
    repository: QuotesRepository
        ): ViewModel() {

//    we can't call getquotes directly as it is a suspending function,we will create custom lazy class in utils
            val quotes by lazyDeferred {
                repository.getQuotes()
            }
}