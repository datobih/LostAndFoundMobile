package com.example.lostandfound.viewmodel

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import com.example.lostandfound.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val mainRepository: MainRepository) : ViewModel() {

fun isFirstTimeUser():Boolean{
    return mainRepository.isFirstTimeUser()
}



    fun setFirstTimeUser(isFirstTime:Boolean){
        mainRepository.setFirstTimeUser(isFirstTime)
    }


    fun setAuthToken(token:String){
        mainRepository.setAuthToken(token)
    }

    fun getAuthToken():String?{
        return mainRepository.getAuthToken()
    }


    fun createImageUri(context: Context): Uri{
            val imageFile = File.createTempFile("captured_${System.currentTimeMillis()}",".jpg",context.cacheDir)
            return FileProvider.getUriForFile(context,"${context.packageName}.provider",imageFile)
    }

}