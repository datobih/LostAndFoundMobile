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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostandfound.Greeting
import com.example.lostandfound.ui.AppButton
import com.example.lostandfound.ui.theme.LostAndFoundTheme
import com.example.lostandfound.ui.theme.Poppins
import com.example.lostandfound.ui.theme.headlineText
import com.example.lostandfound.ui.theme.subText

@Composable
fun OpeningScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


    Box(modifier = Modifier.padding(top = 130.dp,start = 40.dp, end = 40.dp)) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .height(273.dp)
                .background(Color.Red)
        )
    }

        Column(modifier = Modifier.padding(horizontal = 71.dp)) {
            Text(text = "Welcome to Lost And Found",
                style = headlineText,
                modifier = Modifier.padding(top = 67.dp),
                textAlign = TextAlign.Center

            )

            Text(text = "Your go-to app for finding lost items and returning found ones. Let’s get started!",
                style = subText,
                modifier = Modifier.padding(top = 11.dp),
                textAlign = TextAlign.Center

            )


            AppButton(text = "Sign In",modifier = Modifier.fillMaxWidth().padding(top = 76.dp ).height(56.dp)){}




        }






}


}



@Preview(showBackground = true)
@Composable
fun Preview() {
    LostAndFoundTheme {
      OpeningScreen()
    }
}