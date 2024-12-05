package com.example.lostandfound.viewmodel

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.compose.material.ripple.rememberRipple
import androidx.core.content.FileProvider
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lostandfound.repository.MainRepository
import com.example.lostandfound.retrofit.AuthTokenDTO
import com.example.lostandfound.retrofit.ItemDTO
import com.example.lostandfound.retrofit.ItemResponseDTO
import com.example.lostandfound.retrofit.LoginDTO
import com.example.lostandfound.retrofit.SignupDTO
import com.example.lostandfound.utils.Constants
import com.example.lostandfound.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val mainRepository: MainRepository) : ViewModel() {

 private val _signupLiveData:MutableLiveData<UIState<Void?>> = MutableLiveData(UIState.InitialState())
    val signupLiveData:MutableLiveData<UIState<Void?>>
    get() = _signupLiveData

    private val _getLostItemsLiveData:MutableLiveData<UIState<List<ItemResponseDTO?>>> = MutableLiveData(UIState.InitialState())
    val getLostItemsLivedata:MutableLiveData<UIState<List<ItemResponseDTO?>>>
        get() = _getLostItemsLiveData

    private val _getFoundItemsLiveData:MutableLiveData<UIState<List<ItemResponseDTO?>>> = MutableLiveData(UIState.InitialState())
    val getLostFoundItemsLivedata:MutableLiveData<UIState<List<ItemResponseDTO?>>>
        get() = _getFoundItemsLiveData



    private val _getAdsItemsLiveData:MutableLiveData<UIState<List<ItemResponseDTO?>>> = MutableLiveData(UIState.InitialState())
    val getAdsItemsLiveData:MutableLiveData<UIState<List<ItemResponseDTO?>>>
        get() = _getAdsItemsLiveData




    private val _loginLiveData:MutableLiveData<UIState<AuthTokenDTO?>> = MutableLiveData(UIState.InitialState())
    val loginLiveData:MutableLiveData<UIState<AuthTokenDTO?>>
        get() = _loginLiveData



    private val _addItemLiveData:MutableLiveData<UIState<Void?>> = MutableLiveData(UIState.InitialState())
    val addItemLiveData:MutableLiveData<UIState<Void?>>
        get() = _addItemLiveData


fun isFirstTimeUser():Boolean{
    return mainRepository.isFirstTimeUser()
}
fun signUp(signupDTO: SignupDTO){
    viewModelScope.launch {

        try{
            mainRepository.signUp(signupDTO).collect{
                _signupLiveData.postValue(it)
            }
        }
        catch(e:Exception){
            _signupLiveData.postValue(UIState.ErrorState(arrayListOf("An error occured make sure your internet is stable and try again.")))
        }

    }

}



    fun login(loginDTO: LoginDTO){
        viewModelScope.launch {

            try{
                mainRepository.login(loginDTO).collect{
                    _loginLiveData.postValue(it)
                }
            }
            catch(e:Exception){
                _loginLiveData.postValue(UIState.ErrorState(arrayListOf("An error occured make sure your internet is stable and try again.")))
                Log.d("MainViewModel",e.message.toString())
            }

        }

    }

    fun createLostItem(itemDTO: ItemDTO){
        viewModelScope.launch {

            try{
                mainRepository.createLostItem(itemDTO).collect{
                    _addItemLiveData.postValue(it)
                }
            }
            catch(e:Exception){
                _loginLiveData.postValue(UIState.ErrorState(arrayListOf("An error occured make sure your internet is stable and try again.")))
                Log.d("MainViewModel",e.message.toString())
            }

        }
    }


    fun getLostItems(){
        viewModelScope.launch {
            try{
                mainRepository.getLostItems().collect{
                    _getLostItemsLiveData.postValue(it)
                }
            }
            catch(e:Exception){
                _getLostItemsLiveData.postValue(UIState.ErrorState(arrayListOf("An error occured make sure your internet is stable and try again.")))
                Log.d("MainViewModel",e.message.toString())
            }
        }
    }



    fun getMyAdsItems(){
        viewModelScope.launch {
            try{
                mainRepository.getMyAdsItems().collect{
                    _getAdsItemsLiveData.postValue(it)
                }
            }
            catch(e:Exception){
                _getAdsItemsLiveData.postValue(UIState.ErrorState(arrayListOf("An error occured make sure your internet is stable and try again.")))
                Log.d("MainViewModel",e.message.toString())
            }
        }
    }


    fun getFoundItems(){
        viewModelScope.launch {
            try{
                mainRepository.getLostItems().collect{
                    _getFoundItemsLiveData.postValue(it)
                }
            }
            catch(e:Exception){
                _getFoundItemsLiveData.postValue(UIState.ErrorState(arrayListOf("An error occured make sure your internet is stable and try again.")))
                Log.d("MainViewModel",e.message.toString())
            }
        }
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
        Constants.imageTempFile = imageFile
        return FileProvider.getUriForFile(context,"${context.packageName}.provider",imageFile)
    }

    fun resetSignupState(){
        _signupLiveData.value = UIState.InitialState()
    }


    fun resetLoginState(){
        _loginLiveData.value = UIState.InitialState()
    }


    fun resetgetAdsLoginState(){
        _getAdsItemsLiveData.value = UIState.InitialState()
    }


    fun resetaddItemState(){
        _addItemLiveData.value = UIState.InitialState()
    }

}