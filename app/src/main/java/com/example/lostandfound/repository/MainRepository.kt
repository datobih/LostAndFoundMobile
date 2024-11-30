package com.example.lostandfound.repository

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.example.lostandfound.retrofit.AuthTokenDTO
import com.example.lostandfound.retrofit.LoginDTO
import com.example.lostandfound.retrofit.NetworkAPIService
import com.example.lostandfound.retrofit.SignupDTO
import com.example.lostandfound.utils.Constants
import com.example.lostandfound.utils.UIState
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.flow
import retrofit2.await
import retrofit2.awaitResponse
import javax.inject.Inject

class MainRepository @Inject constructor(val sharedPreferences: SharedPreferences,
    val networkAPIService: NetworkAPIService) {
    val gson = Gson()
    fun isFirstTimeUser():Boolean{

        return sharedPreferences.getBoolean(Constants.IS_FIRST_TIME_USER,true)
    }


    fun signUp(signupDTO: SignupDTO)= flow<UIState<Void?>>{
        emit(UIState.LoadingState())

        val response = networkAPIService.signupUser(signupDTO).awaitResponse()
        if(response.isSuccessful){
            emit(UIState.SuccessState(null))
        }
        else{

            val errorBody = response.errorBody()?.string()
            val errorObj = gson.fromJson(errorBody, JsonObject::class.java)
            val jsonErrorMessages = errorObj.get("non_field_errors").asJsonArray
            val errorMessages = ArrayList<String>()
            for (i in 0 until jsonErrorMessages.size()) {
                errorMessages.add(jsonErrorMessages.get(i).asString)
            }
            emit(UIState.ErrorState(errorMessages!!))
            Log.d("SIGNUPPPP", "signupUser: $errorMessages")

        }

    }



    fun login(loginDTO: LoginDTO)= flow<UIState<AuthTokenDTO?>>{
        emit(UIState.LoadingState())

        val response = networkAPIService.loginUser(loginDTO).awaitResponse()
        if(response.isSuccessful){
            emit(UIState.SuccessState(response.body()))
        }
        else{

            val errorBody = response.errorBody()?.string()
            val errorObj = gson.fromJson(errorBody, JsonObject::class.java)
            val jsonErrorMessages = errorObj.get("non_field_errors").asJsonArray
            val errorMessages = ArrayList<String>()
            for (i in 0 until jsonErrorMessages.size()) {
                errorMessages.add(jsonErrorMessages.get(i).asString)
            }
            emit(UIState.ErrorState(errorMessages!!))
            Log.d("Login", "Login: $errorMessages")

        }

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