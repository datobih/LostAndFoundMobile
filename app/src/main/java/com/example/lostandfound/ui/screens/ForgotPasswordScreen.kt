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
import androidx.compose.ui.unit.dp
import com.example.lostandfound.ui.AppButtonBlack
import com.example.lostandfound.ui.theme.headlineBigText
import com.example.lostandfound.ui.theme.labelTextStyle

@Composable
fun ForgotPasswordScreen(){

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

            Text(
                text = "Forgot Password?",
                style = headlineBigText,
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp,top = 70.dp),
                textAlign = TextAlign.Left,


            )


            Text(
                text = "Don’t worry! It happens. Please enter the\nemail associated with your account.",
                style = labelTextStyle,
                modifier = Modifier.fillMaxWidth().padding(top=15.dp, start = 20.dp,end=20.dp),
                textAlign = TextAlign.Left,
                color = Color.Black

            )



            Column(modifier = Modifier.fillMaxWidth().padding(top = 38.dp, start = 20.dp, end = 20.dp)) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(), label = {
                        Text(text = "Enter your email Address", style = labelTextStyle)
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

            }






            AppButtonBlack("Send code",Modifier.fillMaxWidth().padding(top = 45.dp, start = 20.dp, end = 20.dp ).height(56.dp)) { }








        }


        Row (modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 48.dp)){
            Text(
                text = "Remember password?",
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