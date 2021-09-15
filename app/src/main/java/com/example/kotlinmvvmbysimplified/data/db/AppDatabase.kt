package com.example.kotlinmvvmwithlaravel.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase()  {
    abstract fun getUserDao():UserDao

    companion object{
        @Volatile //this variable will be immediately visible to all the other threads
        private var instance : AppDatabase?=null
        private val lock = Any () //to make sure we don't create two instance of database

        operator fun invoke(context:Context)= instance ?: synchronized(lock){
            instance ?: buildDatabase(context).also {
            instance=it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "Mydatabase.db"
            ).build()



    }
}