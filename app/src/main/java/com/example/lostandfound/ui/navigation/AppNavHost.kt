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
import com.example.lostandfound.ui.screens.LoginScreen
import com.example.lostandfound.ui.screens.OnboardingScreen


object OnboardingScreenRef
object LoginScreenRef
object CreateAccountScreenRef
object ForgotPasswordScreenRef
object ForgotPasswordVerificationScreenRef
object HomeScreenRef

@Composable
fun AppNavHost(mainNavController: NavHostController) {


    NavHost(navController = mainNavController, startDestination = HomeScreenRef ){
        composable<HomeScreenRef>{ Homescreen() }
        composable<OnboardingScreenRef> { OnboardingScreen()  }
        composable<CreateAccountScreenRef> { CreateAccountScreen()  }
        composable<LoginScreenRef> { LoginScreen() }
        composable<ForgotPasswordScreenRef> { ForgotPasswordScreen() }
        composable<ForgotPasswordVerificationScreenRef> { ForgotPasswordVerificationScreen() }

    }

}