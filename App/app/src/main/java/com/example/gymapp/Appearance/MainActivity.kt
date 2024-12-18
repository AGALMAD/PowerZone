package com.example.gymapp.Appearance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.compose.AppTheme
import com.example.gymapp.Appearance.Navegationdrawer.NavigationDrawer
import com.example.gymapp.GymApi.ViewModels.AuthViewModel
import com.example.gymapp.GymApi.ViewModels.BodyPartsViewModel
import com.example.gymapp.GymApi.ViewModels.ExercisesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val bodyPartsViewModel: BodyPartsViewModel by viewModels()
                val exercisesViewModel: ExercisesViewModel by viewModels()
                val authViewModel: AuthViewModel by viewModels()
                NavigationDrawer(bodyPartsViewModel,exercisesViewModel,authViewModel)
            }
        }
    }

}




