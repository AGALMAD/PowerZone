package com.example.gymapp.Model

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.gymapp.AboutApp

sealed class Routes(route : String) {

    object Home : Routes("home")
    object AboutUs : Routes("aboutus")
    object Settings : Routes("settings")




}