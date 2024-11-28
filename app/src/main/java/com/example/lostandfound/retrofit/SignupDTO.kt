package com.example.lostandfound.retrofit

import com.google.gson.annotations.SerializedName

class SignupDTO(
    val email: String,
    val password: String,
    @SerializedName("confirm_password")
    val confirmPassword: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("phone_number")
    val phoneNumber:String
)