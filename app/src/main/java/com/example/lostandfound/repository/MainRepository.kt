package com.example.lostandfound.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.lostandfound.retrofit.NetworkAPIService
import com.example.lostandfound.utils.Constants
import javax.inject.Inject

class MainRepository @Inject constructor(val sharedPreferences: SharedPreferences,
    val networkAPIService: NetworkAPIService) {
    fun isFirstTimeUser():Boolean{

        return sharedPreferences.getBoolean(Constants.IS_FIRST_TIME_USER,true)
    }

    fun setFirstTimeUser(isFirstTime:Boolean){
        sharedPreferences.edit(commit = true) {
            putBoolean(Constants.IS_FIRST_TIME_USER,isFirstTime)
        }
    }

    fun setAuthToken(token:String){
        sharedPreferences.edit(commit = true){
            putString(Constants.AUTH_TOKEN,token)
        }
    }

    fun getAuthToken():String?{
       return sharedPreferences.getString(Constants.AUTH_TOKEN,null)
    }
}