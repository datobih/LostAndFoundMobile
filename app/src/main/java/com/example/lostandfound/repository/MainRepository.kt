package com.example.lostandfound.repository

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.example.lostandfound.retrofit.AuthTokenDTO
import com.example.lostandfound.retrofit.ItemDTO
import com.example.lostandfound.retrofit.ItemResponseDTO
import com.example.lostandfound.retrofit.LoginDTO
import com.example.lostandfound.retrofit.NetworkAPIService
import com.example.lostandfound.retrofit.QueryDTO
import com.example.lostandfound.retrofit.SignupDTO
import com.example.lostandfound.retrofit.UserProfileDTO
import com.example.lostandfound.utils.Constants
import com.example.lostandfound.utils.UIState
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
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
            Log.d("SOMN", "SOMN: $errorBody")
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

    fun createLostItem(itemDTO: ItemDTO) = flow<UIState<Void?>>{
        emit((UIState.LoadingState()))

        val name = itemDTO.name.toRequestBody("application/json".toMediaTypeOrNull())
        val description = itemDTO.description.toRequestBody("application/json".toMediaTypeOrNull())
        val category = itemDTO.category.toRequestBody("application/json".toMediaTypeOrNull())
        val location = itemDTO.location.toRequestBody("application/json".toMediaTypeOrNull())
        val imageBody  = itemDTO.image.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("image",itemDTO.image.name,imageBody)

        val response = networkAPIService.postAddItem(tokenVal ="Bearer ${getAuthToken()!!}" ,image = imagePart,
            name = name, description = description,
            category = category, location = location).awaitResponse()

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
            Log.d("Add Item", "Add Item: $errorMessages")

        }


    }


    fun getLostItems()= flow<UIState<List<ItemResponseDTO?>>>{
        emit(UIState.LoadingState())

        val response = networkAPIService.getLostItems().awaitResponse()
        if(response.isSuccessful){
            emit(UIState.SuccessState(response.body()!!))
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
            Log.d("Get Lost Items", "Get Lost Items: $errorMessages")

        }

    }

    fun searchItems(query:String)= flow<UIState<List<ItemResponseDTO?>>>{
        emit(UIState.LoadingState())

        val response = networkAPIService.postSearchItem(tokenVal ="Bearer ${getAuthToken()!!}",
            QueryDTO(query)
        ).awaitResponse()
        if(response.isSuccessful){
            emit(UIState.SuccessState(response.body()!!))
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
            Log.d("Search Items", "Search Items: $errorMessages")

        }

    }



    fun getMyAdsItems()= flow<UIState<List<ItemResponseDTO?>>>{
        emit(UIState.LoadingState())

        val response = networkAPIService.getMyAdsItems(tokenVal ="Bearer ${getAuthToken()!!}").awaitResponse()
        if(response.isSuccessful){
            emit(UIState.SuccessState(response.body()!!))
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
            Log.d("Get Lost Items", "Get Lost Items: $errorMessages")

        }

    }


    fun getProfileData()= flow<UIState<UserProfileDTO?>>{

        emit(UIState.LoadingState())

        val response = networkAPIService.getProfileData(tokenVal ="Bearer ${getAuthToken()!!}").awaitResponse()
        if(response.isSuccessful){
            emit(UIState.SuccessState(response.body()!!))
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
            Log.d("Get Profile Detail", "Get Profile Detail: $errorMessages")

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

    fun logout(){
        sharedPreferences.edit(commit = true){
            putString(Constants.AUTH_TOKEN,null)
        }
    }

    fun getAuthToken():String?{
       return sharedPreferences.getString(Constants.AUTH_TOKEN,null)
    }
}