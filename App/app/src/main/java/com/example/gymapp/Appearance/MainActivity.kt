package com.example.gymapp.Appearance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.example.compose.AppTheme
import com.example.gymapp.Appearance.Navegationdrawer.NavigationDrawer
import com.example.gymapp.GymApi.ViewModels.Auth.AuthViewModel
import com.example.gymapp.GymApi.ViewModels.BodyPartsViewModel
import com.example.gymapp.GymApi.ViewModels.ExercisesViewModel
import com.example.gymapp.Room.Databases.TasksDatabase
import com.example.gymapp.Room.Repositories.TasksRepository
import com.example.gymapp.Room.ViewModels.TasksViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val bodyPartsViewModel: BodyPartsViewModel by viewModels()
                val exercisesViewModel: ExercisesViewModel by viewModels()
                val authViewModel: AuthViewModel by viewModels()
                
                //Variables necesarias para task view model
                val context = LocalContext.current
                val db = Room.databaseBuilder(context, TasksDatabase::class.java, "tasks2").build()
                val tasksDao = db.TasksDao()
                val tasksRepository = TasksRepository(tasksDao)
                val tasksViewModel = TasksViewModel(tasksRepository)

                NavigationDrawer(bodyPartsViewModel,exercisesViewModel,authViewModel, tasksViewModel)

            }
        }
    }

}




