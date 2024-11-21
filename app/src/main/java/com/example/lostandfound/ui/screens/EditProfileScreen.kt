package com.example.lostandfound.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.example.lostandfound.ui.theme.labelTextStyle
import com.example.lostandfound.ui.theme.text18SB
import androidx.compose.material.Text as Text

@Composable
fun EditProfileScreen(){

Column(modifier = Modifier.fillMaxSize()) {

    var name by remember {
        mutableStateOf("")
    }


    var email by remember {
        mutableStateOf("")
    }

    var phoneNumber by remember {
        mutableStateOf("")
    }

    var gender by remember {
        mutableStateOf("")
    }

    var address by remember {
        mutableStateOf("")
    }

    Text(text = "Edit Profile", style = text18SB,
        modifier = Modifier.padding(top= 22.dp).align(Alignment.CenterHorizontally)
    )


    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(), label = {
            Text(text = "Full Name", style = labelTextStyle)
        },
        onValueChange = {
            name = it
        }, value = name, colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color(0x14000000),
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )



    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth().padding(top = 22.dp), label = {
            Text(text = "Email", style = labelTextStyle)
        },
        onValueChange = {
            email = it
        }, value = email, colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color(0x14000000),
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )


    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth().padding(top = 22.dp), label = {
            Text(text = "Phone Number", style = labelTextStyle)
        },
        onValueChange = {
            phoneNumber = it
        }, value = phoneNumber, colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color(0x14000000),
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth().padding(top = 22.dp), label = {
            Text(text = "Gender", style = labelTextStyle)
        },
        onValueChange = {
            gender = it
        }, value = gender, colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color(0x14000000),
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth().padding(top = 22.dp), label = {
            Text(text = "Address", style = labelTextStyle)
        },
        onValueChange = {
            address = it
        }, value = address, colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color(0x14000000),
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )

}


}