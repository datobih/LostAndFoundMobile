package com.example.lostandfound.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lostandfound.ui.ItemCard
import com.example.lostandfound.ui.theme.text18SB

@Composable
fun RecoveredScreen(){

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Recovered Items", style = text18SB,
            modifier = Modifier.padding(top= 22.dp).align(Alignment.CenterHorizontally)
        )




        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(top = 22.dp)) {

            items(23){
                ItemCard("Book","THis da")

            }
        }

    }


}