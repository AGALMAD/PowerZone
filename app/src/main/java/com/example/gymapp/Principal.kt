package com.example.gymapp

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gymapp.ui.theme.misFormas


@Composable
fun Principal(navController: NavHostController){
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InsertTitle(context)
        Spacer(modifier = Modifier.height(26.dp))

        InsertLogoImage()
        Spacer(modifier = Modifier.height(100.dp))

        InsertButtos(context, navController)

    }

}

@Composable
fun InsertTitle(context: Context){
    //Titulo sobre nosotros
    Text(
        text = context.getString(R.string.appTitle),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.displayLarge,
        modifier = Modifier.padding(30.dp)
    )
}


@Composable
fun InsertButtos(context : Context, navController: NavHostController){

    // Botón para ir a About Us
    Button(
        onClick = {navController.navigate(Destination.AboutUs.route)},
        shape = misFormas.small,
        modifier = Modifier.width(250.dp)

    ) {
        Text(text = context.getString(R.string.about_us_title),
            style = MaterialTheme.typography.headlineSmall,
        )
    }


    Spacer(modifier = Modifier.height(20.dp))


    // Botón para ir a About App
    Button(onClick = {navController.navigate(Destination.AboutApp.route)},
        shape = misFormas.small,
        modifier = Modifier.width(250.dp)

    ) {
        Text(text = context.getString(R.string.about_app_title),
            style = MaterialTheme.typography.headlineSmall,
        )
    }

    Spacer(modifier = Modifier.height(20.dp))


    // Botón para ir a Settings
    Button(onClick =
        {navController.navigate(Destination.Settings.route)}
    ,
        shape = misFormas.small,
        modifier = Modifier.width(250.dp)

    ) {
        Text(text = context.getString(R.string.settingsTitle),
            style = MaterialTheme.typography.headlineSmall,
        )
    }

}

@Composable
fun InsertLogoImage(){
    //Cambia la imagen según si está en modo claro u oscuro
    Image(
        painter = if(isSystemInDarkTheme() )
            painterResource(R.drawable.logo_sin_fondo_blanco)
        else
            painterResource(R.drawable.logo_sin_fondo_negro),
        contentDescription = "Imagen Logo principal",
        modifier = Modifier.size(200.dp)
    )
}