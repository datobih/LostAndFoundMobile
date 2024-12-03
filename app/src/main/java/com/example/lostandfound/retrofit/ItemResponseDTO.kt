package com.example.lostandfound.retrofit

import com.google.gson.annotations.SerializedName
import java.io.File

class ItemResponseDTO(
    val id:String,

    @SerializedName("is_found")
    val isFound:Boolean,
    @SerializedName("date_updated")
    val dateUpdated:String,
    val name:String,
    val location:String,
    val image: String,
    val description:String,
    val category: String,
) {
}