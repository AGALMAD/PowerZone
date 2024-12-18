package com.example.gymapp.Appearance.Views

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import com.example.gymapp.Appearance.Data.Routes
import com.example.gymapp.R

@Composable
fun AboutUsContent(navController: NavHostController) {
    val context = LocalContext.current

    // Barra de scroll que recuerda por donde está
    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // Activar scroll vertical
            .background(MaterialTheme.colorScheme.background), //!!!!!! Importante para que cambie el color de fondo
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        //Titulo sobre nosotros
        Text(
            text = context.getString(R.string.about_us_title),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displayLarge,
            modifier =  Modifier.padding(30.dp)
        )

        //Cambia la imagen según si está en modo claro u oscuro
        Image(
            painter = if(isSystemInDarkTheme() )
                painterResource(R.drawable.logo_sin_fondo_blanco)
            else
                painterResource(R.drawable.logo_sin_fondo_negro),
            contentDescription = "Imagen Logo principal",
            modifier = Modifier.size(300.dp)
                .clickable { navController.navigate(Routes.Principal.route) }
        )

        //Texto sobre nosotros
        Text(
            text = context.getString(R.string.about_us_text),
            textAlign = TextAlign.Justify,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyLarge,
            modifier =  Modifier.padding(30.dp)
        )

        Text(
            text = context.getString(R.string.authors_text),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(30.dp)
        )

        Image(
            painterResource(R.drawable.ale),
            contentDescription = "Imagen Ale",
            modifier = Modifier.size(180.dp).clip(CircleShape) // Hace la imagen redonda
        )

        Text(
            text = context.getString(R.string.description_Ale),
            textAlign = TextAlign.Justify,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(15.dp)
        )

        Image(
            painterResource(R.drawable.dani),
            contentDescription = "Imagen Daniel",
            modifier = Modifier.size(180.dp).clip(CircleShape)
        )

        Text(
            text = context.getString(R.string.description_Daniel),
            textAlign = TextAlign.Justify,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(15.dp)
        )

        Row (horizontalArrangement = Arrangement.spacedBy(10.dp)){
            Image(
                painter = if(isSystemInDarkTheme() )
                    painterResource(R.drawable.instagram_logo_blanco)
                else
                    painterResource(R.drawable.instagram_logo_negro),
                contentDescription = "Instagram logo",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        val instagramIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/") //pasa de estring a Uri
                        )
                        context.startActivity(instagramIntent)
                    }
            )

            Image(
                painter = if(isSystemInDarkTheme() )
                    painterResource(R.drawable.x_logo_blanco)
                else
                    painterResource(R.drawable.x_logo_negro),
                contentDescription = "Instagram logo",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        val instagramIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://x.com/")
                        )
                        context.startActivity(instagramIntent)
                    }
            )

            Image(
                painter = if(isSystemInDarkTheme() )
                    painterResource(R.drawable.yt_logo_blanco)
                else
                    painterResource(R.drawable.yt_logo_negro),
                contentDescription = "Instagram logo",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        val instagramIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://youtu.be/xvFZjo5PgG0?si=vucKhT0IF3JnBCyx")
                        )
                        context.startActivity(instagramIntent)
                    }
            )
        }

            // Línea divisoria
            HorizontalDivider(
                color = Color.Gray,
                modifier = Modifier,
                thickness = 2.dp
            )

            Text(
                text = context.getString(R.string.licence_text),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(30.dp)
            )
    }
}