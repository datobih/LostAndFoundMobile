package com.example.lostandfound.retrofit

import com.google.gson.annotations.SerializedName

class ItemResponseDTO(
    val id:String,

    @SerializedName("is_found")
    val isFound:Boolean,
    @SerializedName("date_created")
    val dateCreated:String,
    val name:String,
    val location:String,
    val image: String,
    val description:String,
    val category: String,
    val contact:String
) {
}