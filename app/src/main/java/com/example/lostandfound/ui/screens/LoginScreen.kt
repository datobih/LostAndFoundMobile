package com.example.lostandfound.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDefaults
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.lostandfound.retrofit.AuthTokenDTO
import com.example.lostandfound.retrofit.LoginDTO
import com.example.lostandfound.ui.AppButtonBlack
import com.example.lostandfound.ui.PasswordOutlineTextField
import com.example.lostandfound.ui.navigation.HomeScreenRef
import com.example.lostandfound.ui.theme.headlineText
import com.example.lostandfound.ui.theme.labelTextStyle
import com.example.lostandfound.utils.Constants
import com.example.lostandfound.utils.UIState
import com.example.lostandfound.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(mainViewModel: MainViewModel,navController: NavHostController){


    val loginUIState by mainViewModel.loginLiveData.observeAsState()
    val coroutineScope = rememberCoroutineScope()
    val snackbarHost = remember{SnackbarHostState() }
    Box(Modifier.fillMaxSize()){




        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var validateStates by rememberSaveable {
                mutableStateOf(false)
            }

            var email by rememberSaveable {

                mutableStateOf("")
            }

            var password by rememberSaveable {

                mutableStateOf("")
            }


            val isEmailValid = Constants.isValidEmail(email)
            val isPasswordValid = password.length>=8

            Text(
                text = "Sign in",
                style = headlineText,
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp,top = 70.dp, bottom = 48.dp),
                textAlign = TextAlign.Left

            )

            when(loginUIState){
                is UIState.InitialState->{

                    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(), label = {
                                Text(text = "Email Address", style = labelTextStyle)
                            },
                            onValueChange = {
                                email = it
                            }, value = email, colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color(0x14000000),
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            ), isError = (validateStates && !isEmailValid),
                            supportingText = {
                                if(validateStates && !isEmailValid){
                                    Text("Email address is not valid")
                                }
                            }
                        )


                        PasswordOutlineTextField(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 11.dp), text = password,label ="Password",onTextChange = {
                            password = it
                        }, isError = (validateStates && !isPasswordValid) , errorMessage = "Password must be at least 8 characters")



                    }


                    Text(
                        text = "Forgot password?",
                        style = labelTextStyle,
                        modifier = Modifier.fillMaxWidth().padding(top=15.dp, start = 20.dp,end=20.dp),
                        textAlign = TextAlign.Right,
                        color = Color.Black

                    )



                    AppButtonBlack("Sign in",Modifier.fillMaxWidth().padding(top = 42.dp, start = 20.dp, end = 20.dp ).height(56.dp)) {
                        validateStates = true

                        if(isEmailValid && isPasswordValid){
                            mainViewModel.login(LoginDTO(email,password))
                            }



                    }




                }
                is UIState.SuccessState->{
                  val data =  (loginUIState as UIState.SuccessState<AuthTokenDTO?>).data!!
                    mainViewModel.setAuthToken(data.accessToken)
                    navController.navigate(HomeScreenRef){
                        navController.popBackStack()
                    }
                }
                is UIState.ErrorState->{
                    LaunchedEffect(true) {
                        coroutineScope.launch{
                            val errorMessage = (loginUIState as UIState.ErrorState<AuthTokenDTO?>).data[0]
                            mainViewModel.resetLoginState()
                            snackbarHost.showSnackbar(errorMessage)

                        }
                    }




                }
                is UIState.LoadingState->{
                    Box(modifier = Modifier.fillMaxSize()){
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                }

                else->{

                }



            }





        }

        if(mainViewModel.loginLiveData.value is UIState.InitialState){
            Row (modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 48.dp)){
                Text(
                    text = "Don't have an account?",
                    style = labelTextStyle,
                    textAlign = TextAlign.Right,
                    color = Color.Black

                )

                Text(
                    text = "Sign up",
                    style = labelTextStyle,
                    textAlign = TextAlign.Right,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 3.dp)

                )
            }
        }

        SnackbarHost(snackbarHost, modifier = Modifier.align(Alignment.BottomCenter))


    }

}