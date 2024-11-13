package com.example.gymapp

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gymapp.ui.theme.misFormas


@Composable
fun Principal(){
    val context = LocalContext.current


    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background), //!!!!!! Importante para que cambie el color de fondo
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Titulo sobre nosotros
        Text(
            text = context.getString(R.string.appTitle),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(30.dp)
        )

        InsertButtos(context)

    }

}


@Composable
fun InsertButtos(context : Context){

    // Botón para ir a AboutApp
    Button(onClick = {
        val intent = Intent(context, AboutApp::class.java)
        context.startActivity(intent)
    },
        shape = misFormas.small
    ) {
        Text(text = context.getString(R.string.about_app_title))
    }

    // Botón para ir a AboutApp
    Button(onClick = {
        val intent = Intent(context, AboutApp::class.java)
        context.startActivity(intent)
    },
        shape = misFormas.small
    ) {
        Text(text = context.getString(R.string.about_app_title))
    }

    // Botón para ir a AboutApp
    Button(onClick = {
        val intent = Intent(context, AboutApp::class.java)
        context.startActivity(intent)
    },
        shape = misFormas.small
    ) {
        Text(text = context.getString(R.string.settingsTitle))
    }

}