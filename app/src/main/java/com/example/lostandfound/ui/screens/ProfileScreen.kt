package com.example.lostandfound.ui.screens

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.lostandfound.R
import com.example.lostandfound.retrofit.ItemResponseDTO
import com.example.lostandfound.retrofit.UserProfileDTO
import com.example.lostandfound.ui.AppButtonBlack
import com.example.lostandfound.ui.theme.text14Medium
import com.example.lostandfound.ui.theme.text16M
import com.example.lostandfound.ui.theme.text16SB
import com.example.lostandfound.ui.theme.text22SB
import com.example.lostandfound.utils.Constants
import com.example.lostandfound.utils.UIState
import com.example.lostandfound.viewmodel.MainViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ProfileScreen(toEditProfile: () -> Unit,mainViewModel: MainViewModel,onLogout:() -> Unit){

val context = LocalContext.current










    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally) {
        val getProfileState by mainViewModel.getProfileDataLiveData.observeAsState()

        var imageUri : Uri  by remember {
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

                    Toast.makeText(context,"COIEJCOJEVOJ",Toast.LENGTH_SHORT).show()

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


        when(getProfileState){
            is UIState.ErrorState -> {
                LaunchedEffect(true) {
                    val errorMessage = (getProfileState as UIState.ErrorState<Void?>).data[0]
                    mainViewModel.resetGetProfileState()
                    Toast.makeText(context,errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
            is UIState.InitialState -> {
                LaunchedEffect(true) {
                    mainViewModel.getProfileData()
                }
            }
            is UIState.LoadingState -> {
                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            is UIState.SuccessState -> {

                val data = (getProfileState as UIState.SuccessState<UserProfileDTO?>).data
                Log.d("ProfileScreen", "ProfileScreen: ${data!!.profileImage}")
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 100.dp)
                        .clip(CircleShape)
                        .size(120.dp)
                        .clickable {
//                            if (cameraPermissionState.status.isGranted) {
//                                cameraLauncher.launch(uri)
//                            } else {
//                                if (cameraPermissionState.status.shouldShowRationale) {
//                                    Toast
//                                        .makeText(
//                                            context,
//                                            "The Camera app is important",
//                                            Toast.LENGTH_SHORT
//                                        )
//                                        .show()
//
//                                    val intent = Intent(
//                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
//                                        Uri.fromParts("package", context.packageName, null)
//                                    )
//
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                    context.startActivity(intent)
//
//                                } else {
//                                    cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
//                                }
//
//
//                            }
//

                        },
                    loading = {
                        Box(
                            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = Color.Black, modifier = Modifier.width(40.dp)
                            )
                        }
                    },
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(if(imageUri != Uri.EMPTY) imageUri
                            else if(data.profileImage!=null) Constants.BASE_URL+data.profileImage
                         else R.drawable.ic_profile_circle).crossfade(true).build(),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop
                )


                Text("${data!!.firstName} ${data!!.lastName}", style = text22SB, modifier = Modifier.padding(top = 15.dp))
                Row(modifier = Modifier.padding(top = 1.dp,bottom= 50.dp)) {
                    Text(
                        "${data!!.email}|",
                        style = text14Medium,

                        )
                    Text(
                        data.phoneNumber,
                        style = text14Medium,

                        )
                }


//                Row(modifier = Modifier
//                    .align(Alignment.Start)
//                    .fillMaxWidth()
//                    .clickable {
//                        toEditProfile()
//                    }
//                    .padding(top = 5.dp, bottom = 5.dp, start = 30.dp), verticalAlignment = Alignment.CenterVertically){
//                    Icon(painter = painterResource(R.drawable.ic_edit_info), contentDescription = "Edit", modifier = Modifier.size(30.dp))
//                    Text("Edit Profile Information", style = text16M, modifier = Modifier.padding(start = 35.dp))
//                }

//                Divider(modifier = Modifier
//                    .fillMaxWidth(.9f)
//                    .padding(top = 10.dp))



//                Row(modifier = Modifier
//                    .align(Alignment.Start)
//                    .padding(top = 20.dp, start = 30.dp), verticalAlignment = Alignment.CenterVertically){
//                    Icon(painter = painterResource(R.drawable.ic_contact), contentDescription = "Contact Us", modifier = Modifier.size(30.dp))
//                    Text("Contact Us", style = text16M, modifier = Modifier.padding(start = 35.dp))
//                }
//


                AppButtonBlack(text = "Logout", modifier = Modifier
                    .fillMaxWidth(.85f)
                    .padding(top = 60.dp)) {

                    mainViewModel.logout()
                    onLogout()


                }



                Text("Delete Account", style = text16SB, modifier = Modifier.padding(top = 15.dp), color = Color(
                    0xFFB68D40
                )
                )




            }
            null -> {

            }
        }







    }

}