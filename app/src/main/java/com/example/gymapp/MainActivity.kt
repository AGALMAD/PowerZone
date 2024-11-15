package com.example.gymapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme

sealed class Destination(val route : String){
    object Principal : Destination("Principal");
    object AboutUs : Destination("AboutUs");
    object AboutApp : Destination("AboutApp");
    object Settings : Destination("Settings");

}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Destination.Principal.route) {
                    composable(Destination.Principal.route) { Principal(navController) }
                    composable(Destination.AboutUs.route) { AboutUsContent(navController) }
                    composable(Destination.AboutApp.route) { AboutAppContent(navController) }
                    composable(Destination.Settings.route) { SettingsContent(navController) }

                }
            }
        }
    }
}




