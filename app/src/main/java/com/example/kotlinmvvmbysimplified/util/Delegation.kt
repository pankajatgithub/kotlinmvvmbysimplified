package com.example.kotlinmvvmbysimplified.util

import kotlinx.coroutines.*

//deferred is basically a job with result
fun<T> lazyDeferred(block : suspend CoroutineScope.()->T):Lazy<Deferred<T>>{

    return lazy {
        GlobalScope.async (start = CoroutineStart.LAZY){
            block.invoke(this)
        }
    }
}