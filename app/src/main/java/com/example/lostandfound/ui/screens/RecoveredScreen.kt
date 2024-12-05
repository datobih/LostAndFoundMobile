package com.example.lostandfound.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lostandfound.ui.ItemCard
import com.example.lostandfound.ui.theme.text18SB
import com.example.lostandfound.utils.UIState
import com.example.lostandfound.viewmodel.MainViewModel

@Composable
fun RecoveredScreen(mainViewModel: MainViewModel){


    LaunchedEffect(true) {
        mainViewModel.getFoundItems()
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Recovered Items", style = text18SB,
            modifier = Modifier.padding(top= 22.dp).align(Alignment.CenterHorizontally)
        )

    val foundItemsState by mainViewModel.getLostFoundItemsLivedata.observeAsState()


        when(foundItemsState){
            is UIState.ErrorState -> {

            }
            is UIState.InitialState -> {

            }
            is UIState.LoadingState -> {

            }
            is UIState.SuccessState -> {

            }
            null -> {

            }
        }


        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(top = 22.dp)) {

            items(23){
                ItemCard("Book","THis da","")

            }
        }

    }


}