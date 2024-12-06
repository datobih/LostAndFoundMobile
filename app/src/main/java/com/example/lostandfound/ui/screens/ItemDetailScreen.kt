package com.example.lostandfound.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.lostandfound.R
import com.example.lostandfound.retrofit.ItemResponseDTO
import com.example.lostandfound.ui.theme.text12N
import com.example.lostandfound.ui.theme.text14Medium
import com.example.lostandfound.ui.theme.text16M
import com.example.lostandfound.ui.theme.text16SB
import com.example.lostandfound.ui.theme.text18SB
import com.example.lostandfound.utils.Constants

@Composable
fun ItemDetailScreen(onBackClick: () -> Unit){

    val item by remember { mutableStateOf(Constants.currentItem!!) }

    Column(modifier = Modifier.fillMaxSize()) {

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 22.dp, start = 13.dp)){

            Icon(Icons.Rounded.ArrowBack, modifier = Modifier.size(24.dp).clickable {
                onBackClick()
            }, contentDescription = "Back",)

            Text(text = "Item Detail", style = text18SB,
                modifier = Modifier.fillMaxWidth().padding(start = 10.dp),
                textAlign = TextAlign.Left
            )

        }



        SubcomposeAsyncImage(model = ImageRequest.Builder(LocalContext.current)
            .data(Constants.BASE_URL_UNSLASHED+item.image).crossfade(true).build(),
            contentDescription = "Product Image",
            modifier = Modifier.fillMaxWidth().height(300.dp).padding(start = 10.dp, end = 10.dp, top = 40.dp),
            loading = {

                Box(
                    modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Color.Black, modifier = Modifier.width(40.dp)
                    )
                }

            }, contentScale = ContentScale.Crop)


        Text(text = item.name, style = text16SB,
            modifier = Modifier.fillMaxWidth().padding(start = 22.dp,top = 18.dp),
        )


        Text(
            item.description,
            style = text14Medium,
            modifier = Modifier.padding(start = 22.dp, top = 8.dp),

        )


        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 22.dp,top = 44.dp)) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.ic_calendar),
                contentScale = ContentScale.Fit,
                contentDescription = "item image"
            )
            Text(
                "Posted on ${item.dateCreated.slice(0..9)}",
                style = text16M,
                modifier = Modifier.padding(start = 4.dp),
                overflow = TextOverflow.Ellipsis
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 20.dp,top = 18.dp)
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.ic_location),
                contentScale = ContentScale.Fit,
                contentDescription = "item image"
            )
            Text(
               "Found at ${item.location}",
                style = text16M,
                modifier = Modifier.padding(start = 4.dp),
                overflow = TextOverflow.Ellipsis
            )
        }


        Text(
            "Contact ${item.contact}",
            style = text16M,
            modifier = Modifier.padding(start = 22.dp,top = 18.dp),
            overflow = TextOverflow.Ellipsis
        )


    }





}