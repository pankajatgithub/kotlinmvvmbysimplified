package com.example.kotlinmvvmbysimplified.data.preferences

import android.content.Context
import android.content.SharedPreferences

//update imported file to androidx
import androidx.preference.PreferenceManager

private const val KEY_SAVED_AT = "key_saved_at"
class PreferenceProvider(
    context: Context
) {
    private val appContext = context.applicationContext
    private val preference : SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun savelastSavedAt(savedAt : String){
        preference.edit().putString(
            KEY_SAVED_AT,
            savedAt
        ).apply()
    }
//    nullable string will be return as first time no value saved
    fun getSavedAt():String?{
    return preference.getString(KEY_SAVED_AT,null)
    }
}