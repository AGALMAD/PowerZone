package com.example.gymapp.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.example.ViewModels.BodyPartsViewModel

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
            // Display the list of credit cards
            LazyColumn {
                items(bodyParts) { bodyPart ->
                    Text(text = bodyPart.id)
                    Text(text = bodyPart.title)
                }
            }
        }
    }
}