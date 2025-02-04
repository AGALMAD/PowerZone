package com.example.gymapp.Appearance

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.navigation.NavHostController
import com.example.gymapp.Appearance.Data.Routes
import com.example.gymapp.Appearance.Themes.misFormas
import com.example.gymapp.GymApi.ViewModels.Auth.AuthState
import com.example.gymapp.GymApi.ViewModels.Auth.AuthViewModel
import com.example.gymapp.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun Principal(navController: NavHostController, authViewModel: AuthViewModel) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(70.dp))
        InsertHeader(context, authViewModel, navController)

        InsertTitle(context.getString(R.string.appTitle))
        Spacer(modifier = Modifier.height(26.dp))

        InsertLogoImage()
        Spacer(modifier = Modifier.height(100.dp))

        InsertButtos(context, navController, authViewModel)

    }

}

@Composable
fun InsertHeader(context: Context, authViewModel: AuthViewModel, navController: NavHostController){

    val authState = authViewModel.authState.collectAsState()

    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(0.dp, 20.dp)
    ) {
        when(authState.value){
            is AuthState.Authenticated -> {

                Text(
                    color = MaterialTheme.colorScheme.primary,
                    text = context.getString(R.string.welcomeText) + " " + authViewModel.getEmail,
                    style = MaterialTheme.typography.bodyLarge,
                )

                TextButton(
                    onClick = { authViewModel.signout() },
                ) {
                    Text(
                        text = context.getString(R.string.singoutTitle),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }

            }

            is AuthState.Unauthenticated ->
            {
                TextButton(
                    onClick = { navController.navigate(Routes.Login.route) },
                ) {
                    Text(
                        text = context.getString(R.string.loginTitle),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

            }
            is AuthState.Error ->{
                Toast.makeText(
                    context,
                    getString(context,1),
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> Unit
        }

    }
}


@Composable
fun InsertTitle(title : String) {
    Text(
        text = title,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.displayLarge,
        modifier = Modifier.padding(30.dp)
    )

}

@Composable
fun InsertLogoImage() {
    //Cambia la imagen según si está en modo claro u oscuro
    Image(
        painter = if (isSystemInDarkTheme())
            painterResource(R.drawable.logo_sin_fondo_blanco)
        else
            painterResource(R.drawable.logo_sin_fondo_negro),
        contentDescription = "Imagen Logo principal",
        modifier = Modifier.size(200.dp)
    )
}


@Composable
fun InsertButtos(context: Context, navController: NavHostController, authViewModel: AuthViewModel) {

    // Botón para ir a Settings
    Button(onClick =
    { navController.navigate(Routes.Settings.route) },
        shape = misFormas.small,
        modifier = Modifier.width(250.dp)

    ) {
        Text(
            text = context.getString(R.string.settingsTitle),
            style = MaterialTheme.typography.headlineSmall,
        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Botón para ir al apartado para aprender la tecnica de los ejercicios
    Button(onClick =
    { navController.navigate(Routes.BodyPartsScreen.route) },
        shape = misFormas.small,
        modifier = Modifier.width(250.dp)

    ) {
        Text(
            text = context.getString(R.string.bodyPartsButtonTitle),
            style = MaterialTheme.typography.headlineSmall,
        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Botón para ir a About Us
    Button(
        onClick = { navController.navigate(Routes.AboutUs.route) },
        shape = misFormas.small,
        modifier = Modifier.width(250.dp)

    ) {
        Text(
            text = context.getString(R.string.about_us_title),
            style = MaterialTheme.typography.headlineSmall,
        )
    }

    Spacer(modifier = Modifier.height(20.dp))


}

