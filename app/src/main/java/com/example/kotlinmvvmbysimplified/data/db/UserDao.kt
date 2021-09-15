package com.example.kotlinmvvmwithlaravel.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinmvvmwithlaravel.data.db.entities.CURRENT_USER_ID
import com.example.kotlinmvvmwithlaravel.data.responses.User
@Dao()
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(user: User):Long //update/insert

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getUser():LiveData<User>

}
