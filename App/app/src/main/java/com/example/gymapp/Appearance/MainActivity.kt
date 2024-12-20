package com.example.gymapp.Appearance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.compose.AppTheme
import com.example.gymapp.Appearance.Navegationdrawer.NavigationDrawer
import com.example.gymapp.GymApi.ViewModels.AuthViewModel
import com.example.gymapp.GymApi.ViewModels.BodyPartsViewModel
import com.example.gymapp.GymApi.ViewModels.ExercisesViewModel
import com.example.gymapp.Room.Daos.TasksDao
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

                val context = LocalContext.current
                val db = Room.databaseBuilder(context, TasksDatabase::class.java, "tasks-db").build()

                val tasksDao = db.TasksDao() // Obtener el DAO
                val tasksRepository = TasksRepository(tasksDao) // Crear el repositorio
                val tasksViewModel = TasksViewModel(tasksRepository) // Crear el ViewModel

                NavigationDrawer(bodyPartsViewModel,exercisesViewModel,authViewModel, tasksViewModel)
            }
        }
    }

}




