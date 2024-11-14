package com.example.lostandfound.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lostandfound.ui.AdItem
import com.example.lostandfound.ui.theme.text18SB

@Composable
fun AdsScreen(){
Box(Modifier.fillMaxSize()){

    Column(Modifier.fillMaxSize().background(Color.White)){


        Text(text = "My Ads", style = text18SB, modifier = Modifier.padding(start = 22.dp,top= 22.dp))



        LazyColumn() {


            items(10){
                AdItem()
            }
        }


    }

    FloatingActionButton(onClick = {}, modifier = Modifier.padding(end = 22.dp, bottom = 22.dp).align(Alignment.BottomEnd),
        backgroundColor = Color.Black,
        contentColor = Color.White
    ) {
        Icon(Icons.Filled.Add,"Add Button")
    }
}





}