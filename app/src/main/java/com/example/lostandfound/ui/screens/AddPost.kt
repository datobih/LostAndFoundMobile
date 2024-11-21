package com.example.lostandfound.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lostandfound.R
import com.example.lostandfound.ui.theme.labelTextStyle
import com.example.lostandfound.ui.theme.text14Medium
import com.example.lostandfound.ui.theme.text18SB

@Composable
fun AddPost(){

    Column(modifier = Modifier.fillMaxSize()) {


        var category by remember{

            mutableStateOf("")
        }


        var location by remember{

            mutableStateOf("")
        }

        var date by remember{

            mutableStateOf("")
        }

        Text(text = "Edit Profile", style = text18SB,
            modifier = Modifier.padding(top= 22.dp).align(Alignment.CenterHorizontally)
        )


        Text("Category",style = text14Medium, modifier = Modifier.padding(top = 22.dp, start = 22.dp))


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(.9f).align(Alignment.CenterHorizontally), label = {
                Text(text = "Select Option", style = labelTextStyle)

            },
            onValueChange = {
                category = it
            }, value = category, colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color(0x14000000),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ), enabled = false,trailingIcon = {
                Icon(Icons.Rounded.KeyboardArrowDown, contentDescription = "")
            },leadingIcon = {
                Icon(painter = painterResource(R.drawable.ic_search), contentDescription = "")
            }
        )


        Text("Location",style = text14Medium, modifier = Modifier.padding(top = 22.dp, start = 22.dp))


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(.9f).align(Alignment.CenterHorizontally), label = {
                Text(text = "Select Location", style = labelTextStyle)

            },
            onValueChange = {
                location = it
            }, value = location, colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color(0x14000000),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ), enabled = false,trailingIcon = {
                Icon(Icons.Rounded.KeyboardArrowDown, contentDescription = "")
            },
            leadingIcon = {
                Icon(painter = painterResource(R.drawable.ic_location), contentDescription = "")
            }
        )


        Text("Date Lost",style = text14Medium, modifier = Modifier.padding(top = 22.dp, start = 22.dp))


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(.9f).align(Alignment.CenterHorizontally), label = {
                Text(text = "Select Date", style = labelTextStyle)

            },
            onValueChange = {
                date = it
            }, value = date, colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color(0x14000000),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ), enabled = false, trailingIcon = {
                Icon(Icons.Rounded.KeyboardArrowDown, contentDescription = "")
            }, leadingIcon = {
                Icon(painter = painterResource(R.drawable.ic_calendar), contentDescription = "")
            }
        )

    }



}