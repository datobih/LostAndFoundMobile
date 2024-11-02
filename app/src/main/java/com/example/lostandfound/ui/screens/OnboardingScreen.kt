package com.example.lostandfound.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.lostandfound.ui.AppButton
import com.example.lostandfound.ui.AppButtonBlack
import com.example.lostandfound.ui.navigation.CreateAccountScreenRef
import com.example.lostandfound.ui.navigation.LoginScreenRef
import com.example.lostandfound.ui.theme.LostAndFoundTheme
import com.example.lostandfound.ui.theme.headlineText
import com.example.lostandfound.ui.theme.subText
import com.example.lostandfound.viewmodel.MainViewModel

@Composable
fun OnboardingScreen(mainViewModel: MainViewModel,navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


    Box(modifier = Modifier.padding(top = 70.dp,start = 40.dp, end = 40.dp)) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .height(273.dp)
                .background(Color.Black)
        )
    }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(text = "Welcome to Lost And Found",
                style = headlineText,
                modifier = Modifier.padding(top = 67.dp),
                textAlign = TextAlign.Center

            )

            Text(text = "Your go-to app for finding lost items and returning found ones.\nLet’s get started!",
                style = subText,
                modifier = Modifier.padding(top = 11.dp),
                textAlign = TextAlign.Center

            )



        }
        AppButtonBlack(text = "Sign In",modifier = Modifier.fillMaxWidth().padding(top = 76.dp, start = 20.dp, end = 20.dp ).height(56.dp)){
            mainViewModel.setFirstTimeUser(false)
            navController.navigate(LoginScreenRef){
                navController.popBackStack()
            }

        }

        AppButton(text = "Create Account",modifier =  Modifier.fillMaxWidth().padding(top = 14.dp , start = 20.dp, end = 20.dp).height(56.dp)) {
            mainViewModel.setFirstTimeUser(false)
            navController.navigate(CreateAccountScreenRef){
                navController.popBackStack()
            }
        }







    }


}



@Preview(showBackground = true)
@Composable
fun Preview() {
    LostAndFoundTheme {
      ForgotPasswordVerificationScreen()
    }
}