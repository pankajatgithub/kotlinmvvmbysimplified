package com.example.kotlinmvvmbysimplified.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinmvvmbysimplified.data.db.AppDatabase
import com.example.kotlinmvvmbysimplified.data.db.entities.Quote
import com.example.kotlinmvvmbysimplified.data.network.MyApi
import com.example.kotlinmvvmbysimplified.data.network.SafeApiRequest
import com.example.kotlinmvvmbysimplified.data.preferences.PreferenceProvider
import com.example.kotlinmvvmbysimplified.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class QuotesRepository(
    private val api : MyApi,
    private val db : AppDatabase,
    private val prefs : PreferenceProvider
) :SafeApiRequest(){
    private val quotes = MutableLiveData<List<Quote>>()
    private val MINIMUM_INTERVAL = 6
    init {
        //always observing livedata s we don't care lifecycle here in repository
        quotes.observeForever{
            saveQuotes(it)
        }
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
//            for verssion lower than 26,we need custom legacy classes,
//            but here we increase min sdk which is not recommended
            prefs.savelastSavedAt(LocalDateTime.now().toString())
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
        val lastSavedAt = prefs.getSavedAt()
        if(lastSavedAt == null || isFetchNeeded( LocalDateTime.parse(lastSavedAt))){
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

//    if local database is older than 6 hours or no data available in local database,
//    we will fetch data from api,fot this we will store the timestamp in sharedpreference just before saving data to local database
    private fun isFetchNeeded(lastSavedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(lastSavedAt,LocalDateTime.now()) > MINIMUM_INTERVAL

    }
}