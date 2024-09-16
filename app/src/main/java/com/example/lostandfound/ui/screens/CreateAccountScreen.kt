package com.example.lostandfound.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lostandfound.R
import com.example.lostandfound.ui.AppButton
import com.example.lostandfound.ui.PasswordOutlineTextField
import com.example.lostandfound.ui.theme.LostAndFoundTheme
import com.example.lostandfound.ui.theme.createAccBG
import com.example.lostandfound.ui.theme.headlineText
import com.example.lostandfound.ui.theme.labelTextStyle

@Composable
fun CreateAccountScreen() {


    Box(Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

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


            Text(
                text = "Create account",
                style = headlineText,
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp,top = 145.dp, bottom = 32.dp),
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
                )


                PasswordOutlineTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp), text = password,label ="Password") {
                    password = it
                }


                PasswordOutlineTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp),text = confirmPassword,label ="Confirm Password") {
                    confirmPassword = it
                }

            }

            AppButton("Create Account",Modifier.fillMaxWidth().padding(top = 58.dp, start = 20.dp, end = 20.dp ).height(56.dp)) { }

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
