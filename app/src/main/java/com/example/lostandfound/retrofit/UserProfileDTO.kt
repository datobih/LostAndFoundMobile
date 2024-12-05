package com.example.lostandfound.retrofit

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

class UserProfileDTO(
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("profile_image")
    val profileImage: String?,
    @SerializedName("phone_number")
    val phoneNumber:String
) {
}