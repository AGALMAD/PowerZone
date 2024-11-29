package com.example.gymapp.Appearance.Views.Authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gymapp.Appearance.Data.Routes
import com.example.gymapp.Appearance.Themes.misFormas
import com.example.gymapp.GymApi.ViewModels.AuthState
import com.example.gymapp.GymApi.ViewModels.AuthViewModel
import com.example.gymapp.R

@Composable
fun UserAccount(navController: NavController, authViewModel: AuthViewModel){

    val context = LocalContext.current

    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value)
    {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate(Routes.Login.route)
            else -> Unit
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Text(
            text = "Welcome",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(30.dp)
        )

        Spacer(modifier = Modifier.width(20.dp))

        // Botón para mostrar los datos del usuario
        Button(
            onClick = { authViewModel.singout() },
            shape = misFormas.small,
            modifier = Modifier.width(250.dp)
        ) {
            Text(
                text = "Cerrar sesión",
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }




}