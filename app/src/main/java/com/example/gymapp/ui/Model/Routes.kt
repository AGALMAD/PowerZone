package com.example.gymapp.ui.Model

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.gymapp.AboutApp

sealed class Routes(val route : String) {

    object Home : Routes("home")
    object AboutUs : Routes("aboutus")
    object Settings : Routes("settings")




}