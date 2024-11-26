package com.example.gymapp.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.ViewModels.BodyPartsViewModel
import com.example.gymapp.R
import androidx.compose.ui.Modifier

@Composable
fun BodyPartsContent(navController: NavHostController, viewModel: BodyPartsViewModel) {
    val bodyParts by viewModel.bodyParts.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchBodyParts()
    }

    Column {
        if (bodyParts.isEmpty()) {
            // Show loading indicator or placeholder
            Text(text = "Loading...")
        } else {
            // Mostrar la lista de partes del cuerpo
            LazyColumn {
                items(bodyParts) { bodyPart ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp) // Espaciado entre elementos
                    ) {
                        Text(
                            text = bodyPart.title,
                            modifier = Modifier.padding(bottom = 4.dp)
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