package com.example.gymapp.Appearance.Data

sealed class Routes(val route : String) {
    data object Principal : Routes("Principal");
    data object AboutUs : Routes("AboutUs");
    data object AboutApp : Routes("AboutApp");
    data object Settings : Routes("Settings");
    data object BodyPartsScreen : Routes("BodyPartsScreen");
    data object ExercisesScreen : Routes("ExercisesScreen")
    data object Login : Routes("Login");
    data object Register : Routes("Register");
}