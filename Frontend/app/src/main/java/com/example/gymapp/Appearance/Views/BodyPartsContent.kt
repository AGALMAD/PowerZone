package com.example.gymapp.Appearance.Views

import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.gymapp.GymApi.ViewModels.BodyPartsViewModel
import com.example.gymapp.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose.backgroundDark
import com.example.gymapp.Appearance.Data.Routes
import com.example.gymapp.Appearance.Principal
import com.example.gymapp.GymApi.ViewModels.ExercisesViewModel
import androidx.activity.viewModels
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
