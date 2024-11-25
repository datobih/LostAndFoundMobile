package com.example.lostandfound.ui.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.example.lostandfound.ui.AppButtonBlack
import com.example.lostandfound.ui.PasswordOutlineTextField
import com.example.lostandfound.ui.theme.headlineText
import com.example.lostandfound.ui.theme.labelTextStyle
import com.example.lostandfound.utils.Constants

@Composable
fun CreateAccountScreen() {


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


            var confirmPassword by rememberSaveable {
                mutableStateOf("")
            }

            var isPasswordVisible by rememberSaveable {
                mutableStateOf(false)
            }


            var phoneNumber by rememberSaveable {
                mutableStateOf("")
            }



            Text(
                text = "Create account",
                style = headlineText,
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp,top = 70.dp, bottom = 32.dp),
                textAlign = TextAlign.Left

            )

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
                    )
                , isError = validateStates && email.isEmpty(), supportingText = {

                    if(validateStates && !Constants.isValidEmail(email)){
                        Text("Email address is not valid")
                    }
                     })


                PasswordOutlineTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 11.dp), text = password,label ="Password", isError =(validateStates && (password.length<8)) , onTextChange = {
                    password = it
                }, errorMessage = "Password must be at least 8 characters")

                PasswordOutlineTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 11.dp),text = confirmPassword,label ="Confirm Password", onTextChange = {
                    confirmPassword = it
                }, isError = (validateStates && (confirmPassword.length<8 || password!=confirmPassword)),
                    errorMessage = if(confirmPassword.length<8) "Confirm password must be at least 8 characters"
                    else  "Passwords do not match" )



                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth().padding(top = 11.dp), label = {
                        Text(text = "Phone Number", style = labelTextStyle)
                    },
                    onValueChange = {
                        phoneNumber = it
                    }, value = phoneNumber, colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color(0x14000000),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    )
                )
            }

            AppButtonBlack("Create Account",Modifier.fillMaxWidth().padding(top = 58.dp, start = 20.dp, end = 20.dp ).height(56.dp)) {

                validateStates = true

            }

        }



        Row (modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 48.dp)){
            Text(
                text = "Already have an account?",
                style = labelTextStyle,
                textAlign = TextAlign.Right,
                color = Color.Black

            )

            Text(
                text = "Sign in",
                style = labelTextStyle,
                textAlign = TextAlign.Right,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 3.dp)

            )
        }
    }


}
