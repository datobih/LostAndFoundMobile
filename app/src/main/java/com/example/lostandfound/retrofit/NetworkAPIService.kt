package com.example.lostandfound.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkAPIService {

    @POST("accounts/signup/")
    fun signupUser(@Body signupDTO:SignupDTO): Call<Void>



}