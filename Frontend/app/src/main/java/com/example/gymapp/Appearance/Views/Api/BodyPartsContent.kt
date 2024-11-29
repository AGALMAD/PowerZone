package com.example.gymapp.Appearance.Views.Api

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.example.gymapp.GymApi.ViewModels.BodyPartsViewModel
import androidx.compose.ui.Modifier
import com.example.gymapp.Appearance.Generics.CreateCard


@Composable
fun BodyPartsContent(navController: NavHostController, viewModel: BodyPartsViewModel) {
    val bodyParts by viewModel.bodyParts.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchBodyParts()
    }


    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        if (bodyParts.isEmpty()) {
            // Muestra una barra circular mientras cargan las imágenes
            CircularProgressIndicator()
        } else {
            // Mostrar la lista de partes del cuerpo
            // carga los elementos visibles
            LazyColumn {
                items(bodyParts) { bodyPart ->

                    CreateCard(
                        bodyPart.title,
                        bodyPart.imageUrl
                    ) { navController.navigate("ExercisesScreen/${bodyPart.id}")}

                }
            }
        }
    }
}
