package com.example.gymapp.Appearance.Views

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.gymapp.GymApi.ViewModels.ExercisesViewModel
import com.example.gymapp.R

@Composable
fun ExercisesContent(navController: NavHostController, viewModel: ExercisesViewModel){
    val exercises by viewModel.exercises.observeAsState(emptyList())
    val context = LocalContext.current

    val id = navController.currentBackStackEntry?.arguments?.getString("id")?.toInt()

    LaunchedEffect(id) {
        if (id != null) {
            viewModel.fetchExercises(id)
        }
    }

    if(exercises.isEmpty()){
        CircularProgressIndicator()
    } else {
        LazyColumn {
            items(exercises) { exercise ->
                Card(
                    onClick = {
                                val videoIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(exercise.videoUrl)
                                )
                                context.startActivity(videoIntent)
                              },
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 12.dp
                    ),
                    colors = CardDefaults.cardColors(
                        contentColor = MaterialTheme.colorScheme.surface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ){
                    Column(Modifier.fillMaxSize()) {
                        Text(
                            text = exercise.title,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.displayLarge,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp)
                        )
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(exercise.imageUrl)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.placeholder),
                            error = painterResource(R.drawable.placeholder),
                            contentDescription = exercise.title,
                            contentScale = ContentScale.Crop, // La imagen se ajusta al espacio disponible
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1.77f) // Ratio entre el ancho y el alto de la imagen
                        )
                    }
                }

            }
        }
    }
}