package com.example.kotlinmvvmbysimplified.data.network.responses

import com.example.kotlinmvvmbysimplified.data.db.entities.Quote

data class QuotesResponse (
    val isSuccessful : Boolean,
    val quotes : List<Quote>
)
