package com.example.lostandfound.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.lostandfound.retrofit.ItemResponseDTO
import com.example.lostandfound.ui.AppButton
import com.example.lostandfound.ui.ItemCard
import com.example.lostandfound.ui.theme.text16SB
import com.example.lostandfound.ui.theme.text18SB
import com.example.lostandfound.utils.UIState
import com.example.lostandfound.viewmodel.MainViewModel

@Composable
fun RecoveredScreen(mainViewModel: MainViewModel){
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()



    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Recovered Items", style = text18SB,
            modifier = Modifier.padding(top= 22.dp).align(Alignment.CenterHorizontally)
        )

    val foundItemsState by mainViewModel.getLostFoundItemsLivedata.observeAsState()


        when(foundItemsState){
            is UIState.ErrorState -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Something went wrong.",style = text16SB)
                        AppButton(text = "Retry", modifier = Modifier.padding(top = 15.dp)) {
                            mainViewModel.resetFoundItemState()
                        }
                    }

                }
//
//                LaunchedEffect(true) {
//                        val errorMessage = (foundItemsState as UIState.ErrorState<Void?>).data[0]
//                        mainViewModel.resetFoundItemState()
//                        Toast.makeText(context,errorMessage, Toast.LENGTH_SHORT).show()
//                }
            }
            is UIState.InitialState -> {
                LaunchedEffect(true) {
                    mainViewModel.getFoundItems()
                }
            }
            is UIState.LoadingState -> {
                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            is UIState.SuccessState -> {
                val data = (foundItemsState as UIState.SuccessState<List<ItemResponseDTO?>>).data
                LazyVerticalGrid(columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(top = 22.dp)) {
                    items(data.size){
                   val content = data[it]!!
                ItemCard(content.name,content.description,content.image,content.location,content.dateCreated.slice(0..9),{})

                    }
                }

            }
            null -> {

            }
        }




    }


}