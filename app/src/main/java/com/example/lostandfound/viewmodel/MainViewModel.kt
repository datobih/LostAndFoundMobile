package com.example.lostandfound.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lostandfound.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val mainRepository: MainRepository) : ViewModel() {

fun isFirstTimeUser():Boolean{
    return mainRepository.isFirstTimeUser()
}



    fun setFirstTimeUser(isFirstTime:Boolean){
        mainRepository.setFirstTimeUser(isFirstTime)
    }



}