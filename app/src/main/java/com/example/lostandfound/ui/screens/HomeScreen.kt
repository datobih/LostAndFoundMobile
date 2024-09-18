package com.example.lostandfound.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.lostandfound.ui.navigation.OnboardingScreenRef
import com.example.lostandfound.viewmodel.MainViewModel

@Composable
fun Homescreen(mainViewModel: MainViewModel,parentNavController: NavHostController){

    LaunchedEffect(true) {
        if(mainViewModel.isFirstTimeUser()){
            parentNavController.navigate(OnboardingScreenRef){
                parentNavController.navigateUp()
            }
        }


    }



}