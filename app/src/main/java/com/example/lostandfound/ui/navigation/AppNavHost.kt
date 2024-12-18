package com.example.lostandfound.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lostandfound.ui.screens.CreateAccountScreen
import com.example.lostandfound.ui.screens.ForgotPasswordScreen
import com.example.lostandfound.ui.screens.ForgotPasswordVerificationScreen
import com.example.lostandfound.ui.screens.Homescreen
import com.example.lostandfound.ui.screens.ItemDetailScreen
import com.example.lostandfound.ui.screens.LoginScreen
import com.example.lostandfound.ui.screens.OnboardingScreen
import com.example.lostandfound.viewmodel.MainViewModel
import kotlinx.serialization.Serializable

@Serializable
object OnboardingScreenRef

@Serializable
object LoginScreenRef

@Serializable
object CreateAccountScreenRef

@Serializable
object ForgotPasswordScreenRef

@Serializable
object ForgotPasswordVerificationScreenRef


@Serializable
object ItemDetailScreenRef

@Composable
fun AppNavHost(mainNavController: NavHostController,mainViewModel: MainViewModel) {


    NavHost(navController = mainNavController, startDestination = HomeScreenRef ){
        composable<HomeScreenRef>{ HomeNavHost(mainViewModel,mainNavController) }
        composable<OnboardingScreenRef> { OnboardingScreen(mainViewModel,mainNavController)  }
        composable<CreateAccountScreenRef> { CreateAccountScreen(mainViewModel,mainNavController)  }
        composable<LoginScreenRef> { LoginScreen(mainViewModel,mainNavController) }
        composable<ForgotPasswordScreenRef> { ForgotPasswordScreen() }
        composable<ForgotPasswordVerificationScreenRef> { ForgotPasswordVerificationScreen() }
        composable<ItemDetailScreenRef>{ ItemDetailScreen({mainNavController.popBackStack()}) }

    }

}