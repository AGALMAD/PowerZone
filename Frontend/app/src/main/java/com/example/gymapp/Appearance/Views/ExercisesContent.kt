package com.example.gymapp.Appearance.Views

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.gymapp.Appearance.Generics.CreateCard
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
        LazyColumn(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            items(exercises) { exercise ->

                CreateCard(
                    exercise.title,
                    exercise.imageUrl
                )
                {
                    val videoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(exercise.videoUrl))
                    context.startActivity(videoIntent)
                }
            }

            //Espaciador al final para mejorar el aspecto
            item {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp))
            }

        }



    }
}