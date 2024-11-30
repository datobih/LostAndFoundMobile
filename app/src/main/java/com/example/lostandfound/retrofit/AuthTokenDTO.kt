package com.example.lostandfound.retrofit

import com.google.gson.annotations.SerializedName

class AuthTokenDTO(
    @SerializedName("access_token")
    val accessToken:String,
    @SerializedName("refresh_token")
    val refreshToken:String,
    @SerializedName("is_active")
    val isActive:String


)