package com.example.lostandfound.utils

import java.io.File
import java.util.regex.Pattern

object Constants {

    val BASE_URL = "http://10.0.2.2:8000/"

    val SHARED_PREF_NAME = "LostAndFound"
    val IS_FIRST_TIME_USER = "IS_FIRST_TIME_USER"
    val AUTH_TOKEN = "AUTH_TOKEN"



    val LOCATIONS = listOf("Braaksma Hall",
        "Alumni Hall","Arnold Guebert Library",
        "Founders Hall","Eberhardt Hall","Wangerin House",
        "Schwermann Hall","Allan Wachowich Centre",
        "Guild Hall","Tegler",""
        )

    val CATEGORIES = listOf("Wallets","Electronics","Jewelrys","Bags","Other Accessories")
    var imageTempFile: File? =null
    val filterOptions = arrayOf("All Items","Wallets","Electronics","Jewelrys","Bags","Other Accessories")

    fun isValidEmail(email: String): Boolean {
        val pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+\$")
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

}