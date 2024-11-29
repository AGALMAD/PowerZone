package com.example.gymapp.Appearance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gymapp.GymApi.ViewModels.BodyPartsViewModel
import com.example.compose.AppTheme
import com.example.gymapp.Appearance.Views.AboutAppContent
import com.example.gymapp.Appearance.Views.AboutUsContent
import com.example.gymapp.Appearance.Data.Routes
import com.example.gymapp.Appearance.Views.Api.BodyPartsContent
import com.example.gymapp.Appearance.Views.Api.ExercisesContent
import com.example.gymapp.Appearance.Views.Authentication.Login
import com.example.gymapp.Appearance.Views.Authentication.Register
import com.example.gymapp.Appearance.Views.SettingsContent
import com.example.gymapp.GymApi.ViewModels.AuthViewModel
import com.example.gymapp.GymApi.ViewModels.ExercisesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()
                val bodyPartsViewModel: BodyPartsViewModel by viewModels()
                val exercisesViewModel: ExercisesViewModel by viewModels()
                val authViewModel: AuthViewModel by viewModels()

                NavHost(navController = navController, startDestination = Routes.Principal.route) {
                    composable(Routes.Principal.route) { Principal(navController) }
                    composable(Routes.AboutUs.route) { AboutUsContent(navController) }
                    composable(Routes.AboutApp.route) { AboutAppContent(navController) }
                    composable(Routes.Settings.route) { SettingsContent(navController) }
                    composable(Routes.BodyPartsScreen.route) { BodyPartsContent(navController, bodyPartsViewModel) }
                    composable(Routes.ExercisesScreen.route) { ExercisesContent(navController, exercisesViewModel) }
                    composable(Routes.Login.route) { Login(navController) }
                    composable(Routes.Register.route) { Register(navController, authViewModel) }

                }
            }
        }
    }

}




