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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gymapp.R


@Composable
fun AboutUsContent(navController: NavHostController) {
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
        )



        //Texto sobre nosotros
        Text(
            text = context.getString(R.string.about_us_text),
            textAlign = TextAlign.Justify,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyLarge,
            modifier =  Modifier.padding(30.dp)

        )


        //Contacto
        Row {
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
                            Uri.parse("https://www.youtube.com/")
                        )
                        context.startActivity(instagramIntent)
                    }


            )



        }

    }

}

