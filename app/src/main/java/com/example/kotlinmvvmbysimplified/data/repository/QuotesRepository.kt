package com.example.kotlinmvvmbysimplified.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinmvvmbysimplified.data.db.AppDatabase
import com.example.kotlinmvvmbysimplified.data.db.entities.Quote
import com.example.kotlinmvvmbysimplified.data.network.MyApi
import com.example.kotlinmvvmbysimplified.data.network.SafeApiRequest
import com.example.kotlinmvvmbysimplified.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotesRepository(
    private val api : MyApi,
    private val db : AppDatabase
) :SafeApiRequest(){
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        //always observing livedata s we don't care lifecycle here in repository
        quotes.observeForever{
            saveQuotes(it)
        }
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
suspend fun getQuotes() : LiveData<List<Quote>>{
    return withContext(Dispatchers.IO){
        fetchQuotes()
        db.getQuoteDao().getQuotes()
    }

}

    private suspend fun fetchQuotes(){
        if(isFetchNeeded()){
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(): Boolean {
        return true

    }
}