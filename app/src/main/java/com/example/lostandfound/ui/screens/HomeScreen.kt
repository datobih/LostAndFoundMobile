package com.example.lostandfound.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.lostandfound.ui.MySearchTextField
import com.example.lostandfound.ui.navigation.OnboardingScreenRef
import com.example.lostandfound.ui.theme.text18SB
import com.example.lostandfound.viewmodel.MainViewModel

@Composable
fun Homescreen(mainViewModel: MainViewModel,parentNavController: NavHostController){



    Column(modifier = Modifier.fillMaxSize().background(Color.White)){


        Text(text = "Campus", style = text18SB, modifier = Modifier.padding(start = 22.dp,top= 22.dp))

MySearchTextField("Search for an Item",{}, onSearch = {})


    }


}