package com.example.lostandfound.ui.screens

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lostandfound.R
import com.example.lostandfound.ui.AppButtonBlack
import com.example.lostandfound.ui.theme.text14Medium
import com.example.lostandfound.ui.theme.text16M
import com.example.lostandfound.ui.theme.text16SB
import com.example.lostandfound.ui.theme.text22SB
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ProfileScreen(toEditProfile: () -> Unit){

val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize().background(Color.White), horizontalAlignment = Alignment.CenterHorizontally) {

        val cameraPermissionState = rememberPermissionState(
            android.Manifest.permission.CAMERA
        )

        Image(
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 100.dp)
                .clip(CircleShape).size(120.dp).clickable {

                    if(cameraPermissionState.status.isGranted){

                        Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }

                    else{

                        if(cameraPermissionState.status.shouldShowRationale){
                            Toast.makeText(context, "The Camera app is important", Toast.LENGTH_SHORT).show()

                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package",context.packageName,null))

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivity(intent)

                        }
                        else{
                            cameraPermissionState.launchPermissionRequest()
                        }


                    }




                },
            painter = painterResource(R.drawable.face),
            contentDescription = "Image",
            contentScale = ContentScale.Crop
        )


        Text("David Ayodele", style = text22SB, modifier = Modifier.padding(top = 15.dp))
        Row(modifier = Modifier.padding(top = 1.dp,bottom= 50.dp)) {
            Text(
                "myemail@domain.com|",
                style = text14Medium,

            )
            Text(
                "+2349067382169",
                style = text14Medium,

            )
        }


       Row(modifier = Modifier.align(Alignment.Start).fillMaxWidth().clickable {
           toEditProfile()
       }.padding( top = 5.dp, bottom = 5.dp,start = 30.dp), verticalAlignment = Alignment.CenterVertically){
           Icon(painter = painterResource(R.drawable.ic_edit_info), contentDescription = "Edit", modifier = Modifier.size(30.dp))
           Text("Edit Profile Information", style = text16M, modifier = Modifier.padding(start = 35.dp))
       }

        Divider(modifier = Modifier.fillMaxWidth(.9f).padding(top = 10.dp))



        Row(modifier = Modifier.align(Alignment.Start).padding(top = 20.dp, start = 30.dp), verticalAlignment = Alignment.CenterVertically){
            Icon(painter = painterResource(R.drawable.ic_contact), contentDescription = "Contact Us", modifier = Modifier.size(30.dp))
            Text("Contact Us", style = text16M, modifier = Modifier.padding(start = 35.dp))
        }



        AppButtonBlack(text = "Logout", modifier = Modifier.fillMaxWidth(.85f).padding(top = 60.dp)) { }



        Text("Delete Account", style = text16SB, modifier = Modifier.padding(top = 15.dp), color = Color(
            0xFFB68D40
        )
        )



    }

}