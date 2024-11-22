package com.example.gymapp.Models

sealed class Routes(val route : String) {
    data object Principal : Routes("Principal");
    data object AboutUs : Routes("AboutUs");
    data object AboutApp : Routes("AboutApp");
    data object Settings : Routes("Settings");
    data object Login : Routes("Login");
    data object Register : Routes("Register");
}