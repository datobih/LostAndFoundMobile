package com.example.lostandfound.ui.screens

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import com.example.lostandfound.R
import com.example.lostandfound.retrofit.AuthTokenDTO
import com.example.lostandfound.retrofit.ItemDTO
import com.example.lostandfound.retrofit.LoginDTO
import com.example.lostandfound.ui.AppButtonBlack
import com.example.lostandfound.ui.DropDownTextField
import com.example.lostandfound.ui.PasswordOutlineTextField
import com.example.lostandfound.ui.navigation.HomeScreenRef
import com.example.lostandfound.ui.theme.labelTextStyle
import com.example.lostandfound.ui.theme.text14Medium
import com.example.lostandfound.ui.theme.text18SB
import com.example.lostandfound.utils.Constants
import com.example.lostandfound.utils.UIState
import com.example.lostandfound.viewmodel.MainViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun AddPost(mainViewModel: MainViewModel,onBack:()->Unit){
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()


    var imageUri : Uri by remember {
        mutableStateOf(Uri.EMPTY)
    }

    val uri by remember {

        mutableStateOf(mainViewModel.createImageUri(context))
    }

    val addItemState by mainViewModel.addItemLiveData.observeAsState()


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


    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(bottom = 20.dp).verticalScroll(rememberScrollState())) {


        var name  by remember{
            mutableStateOf("")
        }

        var description  by remember{
            mutableStateOf("")
        }

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




        when(addItemState){

            is UIState.InitialState-> {


                Text(text = "Post Lost Item", style = text18SB,
                    modifier = Modifier.padding(top= 22.dp).align(Alignment.CenterHorizontally)
                )

                Text("Name",style = text14Medium, modifier = Modifier.padding(top = 22.dp, start = 22.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(.9f).align(Alignment.CenterHorizontally), label = {
                        Text(text = "What is the name of the item?", style = labelTextStyle)
                    },
                    onValueChange = {
                        name = it
                    }, value =name, colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color(0x14000000),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    )
                )

                Text("Description",style = text14Medium, modifier = Modifier.padding(top = 22.dp, start = 22.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(.9f).align(Alignment.CenterHorizontally), label = {
                        Text(text = "Describe the item", style = labelTextStyle)
                    },
                    onValueChange = {
                        description = it
                    }, value =description, colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color(0x14000000),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    )
                )



                Text("Category",style = text14Medium, modifier = Modifier.padding(top = 22.dp, start = 22.dp))



                DropDownTextField(Constants.CATEGORIES, "Select Option",R.drawable.ic_search,category,{category = it})





                Text("Location",style = text14Medium, modifier = Modifier.padding(top = 22.dp, start = 22.dp))




                DropDownTextField(Constants.LOCATIONS, "Select Location",R.drawable.ic_location,location,{location = it})


                Text("Date Lost",style = text14Medium, modifier = Modifier.padding(top = 22.dp, start = 22.dp))

                DropDownTextField(listOf("A","B"), "Select Date",R.drawable.ic_calendar,date,{date = it})



                Text("Contact",style = text14Medium, modifier = Modifier.padding(top = 22.dp, start = 22.dp))


                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(.9f).align(Alignment.CenterHorizontally), label = {
                        Text(text = "Phone Number", style = labelTextStyle)
                    },
                    onValueChange = {
                        if(phoneNumber.length<=12)
                        phoneNumber = it
                    }, value =phoneNumber, colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color(0x14000000),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    )
                )



                Text("Upload Item Picture",style = text14Medium, modifier = Modifier.padding(top = 22.dp, start = 22.dp))


                Icon(painter = painterResource(R.drawable.ic_upload), tint = Color.Black, contentDescription = "Upload Image",
                    modifier = Modifier.padding(top = 20.dp, bottom = 5.dp).size(70.dp).align(Alignment.CenterHorizontally).clickable {

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


                AnimatedVisibility((category.isNotEmpty() && location.isNotEmpty()
                        && phoneNumber.isNotEmpty() && date.isNotEmpty() && imageUri != Uri.EMPTY
                        && name.isNotEmpty() && description.isNotEmpty()),
                    modifier = Modifier.align(Alignment.CenterHorizontally) ) {

                    AppButtonBlack("Post",Modifier.fillMaxWidth(.9f).padding(top = 42.dp).height(56.dp)) {
                        val file = Constants.imageTempFile!!
                        mainViewModel.createLostItem(ItemDTO(image = file, name = name,
                            description = description,
                            location = location, category = category))


                    }
                }




            }
            is UIState.SuccessState->{
                Toast.makeText(context,"Item added successfully",Toast.LENGTH_SHORT).show()
                onBack()
            }
            is UIState.ErrorState->{
                LaunchedEffect(true) {
                    coroutineScope.launch{
                        val errorMessage = (addItemState as UIState.ErrorState<Void?>).data[0]
                        mainViewModel.resetaddItemState()
                       Toast.makeText(context,errorMessage,Toast.LENGTH_SHORT).show()

                    }
                }


            }

            is UIState.LoadingState->{
                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

            }

            else->{

            }


        }

    }



}