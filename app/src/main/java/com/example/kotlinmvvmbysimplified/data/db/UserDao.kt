package com.example.kotlinmvvmbysimplified.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinmvvmbysimplified.data.db.entities.CURRENT_USER_ID
import com.example.kotlinmvvmbysimplified.data.db.entities.User


@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun upsert(user: User):Long //update/insert

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getUser():LiveData<User>

}
