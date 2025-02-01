package com.example.gymapp.Appearance.Views.Authentication

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gymapp.Appearance.Data.Routes
import com.example.gymapp.Appearance.InsertTitle
import com.example.gymapp.Appearance.Themes.misFormas
import com.example.gymapp.GymApi.ViewModels.Auth.AuthState
import com.example.gymapp.GymApi.ViewModels.Auth.AuthViewModel
import com.example.gymapp.R

@Composable
fun Register(navController: NavHostController, authViewModel: AuthViewModel){
    val context = LocalContext.current

    /*var name by remember {
        mutableStateOf("")
    }*/

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    /*var repeatPassword by remember {
        mutableStateOf("")
    }*/

    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate(Routes.Principal.route)
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).mesagge, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        )
    {
        InsertTitle(context.getString(R.string.singUpTitle))

        Spacer( modifier = Modifier.height(20.dp))

        /*OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text(text = context.getString(R.string.name_label))
            }
        )

        Spacer( modifier = Modifier.height(12.dp))*/

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = context.getString(R.string.emailWord))
            }
        )

        Spacer( modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = context.getString(R.string.passWord))
            }
        )

        Spacer( modifier = Modifier.height(50.dp))

        // Botón para registrarse
        Button(
            onClick = {
                authViewModel.singup(email,password)
            },
            shape = misFormas.medium,
        ) {
            Text(
                text = context.getString(R.string.singUpTitle),
                style = MaterialTheme.typography.headlineSmall,
            )
        }

        Spacer( modifier = Modifier.height(20.dp))

        //Text button para iniciar sesión
        TextButton(onClick = {navController.navigate(Routes.Login.route) }
        ) {
            Text(
                text = context.getString(R.string.haveAccountText),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }


    }
}