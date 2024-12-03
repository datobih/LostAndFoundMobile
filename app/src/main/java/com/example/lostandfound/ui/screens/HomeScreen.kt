package com.example.lostandfound.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.lostandfound.retrofit.AuthTokenDTO
import com.example.lostandfound.retrofit.ItemResponseDTO
import com.example.lostandfound.ui.FilterItem
import com.example.lostandfound.ui.ItemCard
import com.example.lostandfound.ui.MySearchTextField
import com.example.lostandfound.ui.OutlinedFilterItem
import com.example.lostandfound.ui.navigation.OnboardingScreenRef
import com.example.lostandfound.ui.theme.text18SB
import com.example.lostandfound.utils.Constants
import com.example.lostandfound.utils.UIState
import com.example.lostandfound.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun Homescreen(mainViewModel: MainViewModel,parentNavController: NavHostController){
    var selectedPosition: Int by remember {
        mutableStateOf(0)
    }

    val context = LocalContext.current

    LaunchedEffect(true) {
        mainViewModel.getLostItems()
    }
    val coroutineScope = rememberCoroutineScope()


    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){


        val getLostItems by mainViewModel.getLostItemsLivedata.observeAsState()


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



        when(getLostItems){
         is UIState.LoadingState->{

             Box(modifier = Modifier.fillMaxSize()){
                 CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
             }

         }

            is UIState.ErrorState -> {
                LaunchedEffect(true) {
                    coroutineScope.launch{
                        val errorMessage = (getLostItems as UIState.ErrorState<ArrayList<String>>).data[0]
                        mainViewModel.resetLoginState()
                        Toast.makeText(context,errorMessage,Toast.LENGTH_LONG).show()

                    }
                }
            }
            is UIState.InitialState -> {

            }
            is UIState.SuccessState -> {
                val data = (getLostItems as UIState.SuccessState<List<ItemResponseDTO?>>).data
                LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(top = 22.dp)) {

                    items(data.size){
                        val content =data[it]!!
                        ItemCard(content.name,content.description, url = content.image,
                            content.location,content.dateUpdated.slice(0..9))

                    }
                }

            }
            null -> {

            }
        }






    }


}