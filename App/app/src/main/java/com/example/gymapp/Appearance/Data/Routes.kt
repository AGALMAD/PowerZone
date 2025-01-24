package com.example.gymapp.Appearance.Data

sealed class Routes(val route : String) {
    data object Principal : Routes("Principal");
    data object AboutUs : Routes("AboutUs");
    data object Settings : Routes("Settings");
    data object BodyPartsScreen : Routes("BodyPartsScreen");
    data object ExercisesScreen : Routes("ExercisesScreen/{id}")
    data object Login : Routes("Login");
    data object Register : Routes("Register");
    data object TasksManager : Routes("TasksManager");
    //data object UserAccount : Routes("UserAccount");

}