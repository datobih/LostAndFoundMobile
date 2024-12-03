package com.example.lostandfound.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.lostandfound.R
import com.example.lostandfound.ui.screens.AddPost
import com.example.lostandfound.ui.screens.Homescreen
import com.example.lostandfound.ui.screens.AdsScreen
import com.example.lostandfound.ui.screens.EditProfileScreen
import com.example.lostandfound.ui.screens.ProfileScreen
import com.example.lostandfound.ui.screens.RecoveredScreen
import com.example.lostandfound.ui.theme.bottomNavText
import com.example.lostandfound.viewmodel.MainViewModel
import kotlinx.serialization.Serializable


@Serializable
object HomeScreenRef

// Home level routes
@Serializable
object HomeLevelHomeRef
@Serializable
object HomeLevelPostRef
@Serializable
object HomeLevelRecoveredRef


@Serializable
object HomeLevelProfileRef

@Serializable
object Profile

@Serializable
object EditProfile


@Serializable
object Posts

@Serializable
object AddPost




data class HomeLevelRoute<T:Any>(val name:String,val route:T,val icon:Int,val selectedIcon:Int)

val homeLevelRoutes = listOf(
    HomeLevelRoute("Home",HomeLevelHomeRef, R.drawable.ic_home,R.drawable.ic_home),
    HomeLevelRoute("My Ads",HomeLevelPostRef,R.drawable.ic_speaker_one,R.drawable.ic_speaker_one),
    HomeLevelRoute("Recovered",HomeLevelRecoveredRef,R.drawable.ic_bag_check,R.drawable.ic_bag_check),
    HomeLevelRoute("Profile",HomeLevelProfileRef,R.drawable.ic_profile,R.drawable.ic_profile)
)


@SuppressLint("RestrictedApi")
@Composable
fun HomeNavHost(mainViewModel: MainViewModel,parentNavController: NavHostController){
    LaunchedEffect(true) {

        if(mainViewModel.getAuthToken() == null){
        if(mainViewModel.isFirstTimeUser()){
            parentNavController.navigate(OnboardingScreenRef){
                parentNavController.popBackStack()
            }
        }
        else{

            parentNavController.navigate(LoginScreenRef){
                parentNavController.popBackStack()
            }
        }

        }

    }
    val homeNavController = rememberNavController()

    Scaffold(

   bottomBar = { BottomNavigation(backgroundColor = Color.White) {

       val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
       val currentDestination = navBackStackEntry?.destination
        homeLevelRoutes.forEach { homeLevelRoute->
            val isSelected = currentDestination?.hierarchy?.any { it.hasRoute(homeLevelRoute.route::class) } == true
            BottomNavigationItem(
                icon= {
                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                        Icon(painter = painterResource(homeLevelRoute.icon), tint = if(isSelected) Color.Black else Color(
                            0x40000000
                        ),contentDescription = "")
                    }

                },
                        label = { Text(homeLevelRoute.name, style = bottomNavText, color =  if(isSelected) Color.Black else Color(
                            0x40000000
                        )) },
                        onClick = {

                            homeNavController.navigate(homeLevelRoute.route){

                                popUpTo(homeNavController.graph.startDestinationId){
                                    saveState = true
                                }

                                restoreState = true
                                launchSingleTop =true

                            }
                        },
                selected = currentDestination?.hierarchy?.any {  it.hasRoute(homeLevelRoute.route::class) } == true
            )
        }
   } }
    ) { innerPadding ->

     NavHost(homeNavController, startDestination = HomeLevelHomeRef, modifier = Modifier.padding(innerPadding)){
         composable<HomeLevelHomeRef>{ Homescreen(mainViewModel, parentNavController) }
         navigation<HomeLevelPostRef>(startDestination = Posts) {
             composable<Posts> { AdsScreen { homeNavController.navigate(AddPost) } }
            composable<AddPost> { AddPost(mainViewModel,{ homeNavController.navigate(Posts){
                homeNavController.popBackStack()
            } }) }
         }
         composable<HomeLevelRecoveredRef> { RecoveredScreen()  }

         navigation<HomeLevelProfileRef>(startDestination = Profile){
             composable<Profile> { ProfileScreen(toEditProfile = { homeNavController.navigate(EditProfile)   },mainViewModel=mainViewModel)}
             composable<EditProfile>{ EditProfileScreen() }
         }

     }
    }



}