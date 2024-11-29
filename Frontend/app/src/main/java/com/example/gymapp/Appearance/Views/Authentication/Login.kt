package com.example.gymapp.Appearance.Views.Authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.gymapp.R

@Composable
fun Login(navController: NavHostController){

    val context = LocalContext.current

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    )
    {
        InsertTitle(context.getString(R.string.loginTitle))

        Spacer( modifier = Modifier.height(20.dp))

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

        // Botón para inicar sesión
        Button(
            onClick = {  },
            shape = misFormas.medium,
        ) {
            Text(
                text = context.getString(R.string.loginTitle),
                style = MaterialTheme.typography.headlineSmall,
            )
        }

        Spacer( modifier = Modifier.height(20.dp))

        //Text button para registrarse
        TextButton(onClick = {navController.navigate(Routes.Register.route) }
        ) {
            Text(
                text = context.getString(R.string.notAccountText),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }


    }

}