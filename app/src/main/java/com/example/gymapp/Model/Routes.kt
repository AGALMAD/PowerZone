package com.example.gymapp.Model

sealed class Routes(val route : String) {
    object Principal : Routes("Principal");
    object AboutUs : Routes("AboutUs");
    object AboutApp : Routes("AboutApp");
    object Settings : Routes("Settings");
    object Login : Routes("Login");
    object Register : Routes("Register");


}