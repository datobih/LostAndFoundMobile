package com.example.lostandfound.ui.screens

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostandfound.R
import com.example.lostandfound.ui.theme.labelTextStyle
import com.example.lostandfound.ui.theme.text14Medium
import com.example.lostandfound.ui.theme.text18SB
import com.example.lostandfound.viewmodel.MainViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun AddPost(mainViewModel: MainViewModel){
    val context = LocalContext.current

    var imageUri : Uri by remember {
        mutableStateOf(Uri.EMPTY)
    }

    val uri by remember {

        mutableStateOf(mainViewModel.createImageUri(context))
    }


    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    val cameraPermissionLauncher = rememberLauncherForActivityResult (contract = ActivityResultContracts.RequestPermission(),
        onResult = {
                success->

            if(success){

                Toast.makeText(context,"COIEJCOJEVOJ", Toast.LENGTH_SHORT).show()

            }



        })




    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = {success ->
            if(success){
                imageUri = uri

            }
            else{

            }
        }

    )


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

        var phoneNumber by remember{
            mutableStateOf("")
        }

        var isExpanded by remember {
            mutableStateOf(false)
        }

        Text(text = "Post Lost Item", style = text18SB,
            modifier = Modifier.padding(top= 22.dp).align(Alignment.CenterHorizontally)
        )


        Text("Category",style = text14Medium, modifier = Modifier.padding(top = 22.dp, start = 22.dp))

            ExposedDropdownMenuBox(modifier = Modifier.align(Alignment.CenterHorizontally),expanded = isExpanded, onExpandedChange = {isExpanded = !isExpanded}) {

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(.9f).menuAnchor(), label = {
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

                ExposedDropdownMenu(modifier = Modifier.fillMaxWidth(),expanded = isExpanded, onDismissRequest = {isExpanded=false}) {
                    listOf("A","B","C").forEachIndexed { index, s ->

                        DropdownMenuItem(
                            text = { Text(s)},
                            onClick = {
                                isExpanded = false
                                category = s
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )


                    }



                }




        }





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


        Text("Contact",style = text14Medium, modifier = Modifier.padding(top = 22.dp, start = 22.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(.9f).align(Alignment.CenterHorizontally), label = {
                Text(text = "Phone Number", style = labelTextStyle)

            },
            onValueChange = {
                phoneNumber = it
            }, value = phoneNumber, colors = OutlinedTextFieldDefaults.colors(
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


        Text("Upload Item Picture",style = text14Medium, modifier = Modifier.padding(top = 22.dp, start = 22.dp))


        Icon(painter = painterResource(R.drawable.ic_upload), tint = Color.Black, contentDescription = "Upload Image",
            modifier = Modifier.padding(20.dp).size(70.dp).align(Alignment.CenterHorizontally).clickable {

                if(cameraPermissionState.status.isGranted){
                    cameraLauncher.launch(uri)
                }

                else{

                    if(cameraPermissionState.status.shouldShowRationale){
                        Toast.makeText(context, "The Camera app is important", Toast.LENGTH_SHORT).show()

                        val intent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package",context.packageName,null))

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(intent)

                    }
                    else{
                        cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                    }


                }




            })



        Text(text = if(imageUri == Uri.EMPTY)"Tap on the icon to select a picture" else "Image captured",style = text14Medium, modifier = Modifier.padding(top = 12.dp,)
            .align(Alignment.CenterHorizontally), fontSize = 10.sp)


    }



}