package com.example.kotlinmvvmbysimplified.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {
//    to perform operation on main thread
    fun main(work:suspend (()->Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
        work()
    }
    //    to perform operation on IO thread
    fun io(work:suspend (()->Unit)) =
        CoroutineScope(Dispatchers.IO).launch {
        work()
    }

}