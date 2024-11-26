package com.example.gymapp.Appearance.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.compose.backgroundDark

@Composable
fun BodyPartsContent(navController: NavHostController, viewModel: BodyPartsViewModel) {
    val bodyParts by viewModel.bodyParts.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchBodyParts()
    }

    Column (modifier = Modifier.background(MaterialTheme.colorScheme.background)){
        if (bodyParts.isEmpty()) {
            // Mustra una barra circular mientras cargan las imágenes
            CircularProgressIndicator()
        } else {
            // Mostrar la lista de partes del cuerpo
            // carga los elementos visibles
            LazyColumn {
                items(bodyParts) { bodyPart ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp) // Espaciado entre elementos
                    ) {
                        Text(
                            text = bodyPart.title,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.displayLarge,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp)
                        )
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(bodyPart.imageUrl)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.placeholder),
                            error = painterResource(R.drawable.placeholder),
                            contentDescription = bodyPart.title,
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