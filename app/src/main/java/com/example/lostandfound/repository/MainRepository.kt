package com.example.lostandfound.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.lostandfound.utils.Constants
import javax.inject.Inject

class MainRepository @Inject constructor(val sharedPreferences: SharedPreferences) {
    fun isFirstTimeUser():Boolean{
        return sharedPreferences.getBoolean(Constants.IS_FIRST_TIME_USER,true)
    }

    fun setFirstTimeUser(isFirstTime:Boolean){
        sharedPreferences.edit(commit = true) {
            putBoolean(Constants.IS_FIRST_TIME_USER,isFirstTime)
        }
    }

}