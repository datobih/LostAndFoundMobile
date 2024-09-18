package com.example.lostandfound.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lostandfound.ui.AppButtonBlack
import com.example.lostandfound.ui.PinField
import com.example.lostandfound.ui.theme.headlineBigText
import com.example.lostandfound.ui.theme.labelTextStyle

@Composable
fun ForgotPasswordVerificationScreen(){

    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = "Please check your\nemail",
            style = headlineBigText,
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp,top = 70.dp),
            textAlign = TextAlign.Left

        )

        Row (modifier = Modifier.fillMaxWidth().padding(start = 20.dp,top = 13.dp, bottom = 34.dp)){
            Text(
                text = "We sent a code to ",
                style = labelTextStyle,
                textAlign = TextAlign.Left,
                color = Color.Black

            )

            Text(
                text = "hello@gmail.com",
                style = labelTextStyle,
                textAlign = TextAlign.Right,
                color = Color.Black,
                fontWeight = FontWeight.Bold,


            )
        }



        PinField()



        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppButtonBlack("Verify",Modifier.fillMaxWidth().padding(top = 45.dp, start = 20.dp, end = 20.dp ).height(56.dp)) { }


            Row (modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 38.dp)){
                Text(
                    text = "Send code again",
                    style = labelTextStyle,
                    textAlign = TextAlign.Right,
                    color = Color.Black

                )

                Text(
                    text = "00:20",
                    style = labelTextStyle,
                    textAlign = TextAlign.Right,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 3.dp)

                )
            }


        }


    }


}