package com.example.lostandfound.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lostandfound.ui.screens.Homescreen
import com.example.lostandfound.viewmodel.MainViewModel
import kotlinx.serialization.Serializable


@Serializable
object HomeScreenRef


@Composable
fun HomeNavHost(mainViewModel: MainViewModel,parentNavController: NavHostController){
    LaunchedEffect(true) {
        if(mainViewModel.isFirstTimeUser()){
            parentNavController.navigate(OnboardingScreenRef){
                parentNavController.popBackStack()
            }
        }
    }



    val navController = rememberNavController()
    NavHost(navController,startDestination=HomeScreenRef){
        composable<HomeScreenRef>{ Homescreen(mainViewModel,navController) }

    }


}