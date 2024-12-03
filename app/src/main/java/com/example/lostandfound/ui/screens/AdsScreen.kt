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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.lostandfound.retrofit.ItemResponseDTO
import com.example.lostandfound.ui.AdItem
import com.example.lostandfound.ui.theme.text18SB
import com.example.lostandfound.utils.UIState
import com.example.lostandfound.viewmodel.MainViewModel

@Composable
fun AdsScreen(mainViewModel:MainViewModel,toAddPost: () -> Unit){
Box(Modifier.fillMaxSize()){


    val context = LocalContext.current

    LaunchedEffect(true) {
        mainViewModel.getFoundItems()
    }
    val coroutineScope = rememberCoroutineScope()


    val getFoundItems by mainViewModel.getLostFoundItemsLivedata.observeAsState()

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)){


        Text(text = "My Ads", style = text18SB, modifier = Modifier.padding(start = 22.dp,top= 22.dp))


        when(getFoundItems){
            is UIState.ErrorState -> {

            }
            is UIState.InitialState -> {

            }
            is UIState.LoadingState -> {
                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            is UIState.SuccessState -> {
                val data = (getFoundItems as UIState.SuccessState<List<ItemResponseDTO?>>).data

                LazyColumn(modifier = Modifier.padding(top = 40.dp)) {
                    items(data.size){
                        val content  = data[it]!!
                        AdItem(content.name,content.location,
                            content.dateUpdated.slice(0..9),content.image)
                    }
                }
            }
            null -> {

            }
        }




    }

    FloatingActionButton(onClick = {
        toAddPost()

    }, modifier = Modifier
        .padding(end = 22.dp, bottom = 22.dp)
        .align(Alignment.BottomEnd),
        backgroundColor = Color.Black,
        contentColor = Color.White
    ) {
        Icon(Icons.Filled.Add,"Add Button")
    }
}





}