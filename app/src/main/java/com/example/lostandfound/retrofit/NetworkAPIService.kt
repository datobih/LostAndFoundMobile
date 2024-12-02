package com.example.lostandfound.retrofit

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface NetworkAPIService {

    @POST("accounts/signup/")
    fun signupUser(@Body signupDTO:SignupDTO): Call<Void>

    @POST("accounts/login/")
    fun loginUser(@Body loginDTO: LoginDTO): Call<AuthTokenDTO>



    @Multipart
    @POST("api/create-lost-item/")
    fun postAddItem(@Part image : MultipartBody.Part,
                    @Part("name") name: RequestBody,
                    @Part("category") category: RequestBody,
                    @Part("location") location: RequestBody,
                    @Part("description") description: RequestBody):Call<Any>
}