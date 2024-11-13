package com.example.lostandfound.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.lostandfound.ui.FilterItem
import com.example.lostandfound.ui.ItemCard
import com.example.lostandfound.ui.MySearchTextField
import com.example.lostandfound.ui.OutlinedFilterItem
import com.example.lostandfound.ui.navigation.OnboardingScreenRef
import com.example.lostandfound.ui.theme.text18SB
import com.example.lostandfound.utils.Constants
import com.example.lostandfound.viewmodel.MainViewModel

@Composable
fun Homescreen(mainViewModel: MainViewModel,parentNavController: NavHostController){
    var selectedPosition: Int by remember {
        mutableStateOf(0)
    }


    Column(modifier = Modifier.fillMaxSize().background(Color.White)){


        Text(text = "Campus", style = text18SB, modifier = Modifier.padding(start = 22.dp,top= 22.dp))

MySearchTextField("Search for an Item",{}, onSearch = {})

LazyRow(modifier = Modifier.padding(start = 22.dp, end = 22.dp, top = 22.dp, bottom = 0.dp)) {

    items(Constants.filterOptions.size){

       FilterItem(Constants.filterOptions[it],selectedPosition == it,{selectedPosition = it})
    }


}
        Row(Modifier.padding(top = 15.dp, start = 22.dp)){
            OutlinedFilterItem("Lost")
            OutlinedFilterItem("Found")
            OutlinedFilterItem("Location")
        }


        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(top = 22.dp)) {

            items(23){
                ItemCard("Book","THis da")

            }
        }


    }


}