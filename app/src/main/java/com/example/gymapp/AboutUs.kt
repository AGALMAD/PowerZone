package com.example.gymapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme

class AboutUs : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                AboutUsContent()

            }
        }
    }
}



@Composable
fun AboutUsContent() {
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
            color = MaterialTheme.colorScheme.scrim,
            style = MaterialTheme.typography.displayMedium,
            modifier =  Modifier.padding(30.dp)


        )

        //Imagen del logo principal
        Image(
            painter = painterResource(R.drawable.logo_sin_fondo_negro),
            contentDescription = "Imagen Logo principal",
            modifier = Modifier.size(300.dp)
        )


        //Texto sobre nosotros
        Text(
            text = context.getString(R.string.about_us_text),
            textAlign = TextAlign.Justify,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge,
            modifier =  Modifier.padding(30.dp)

            )


        //Contacto
        Row {
            Image(
                painter = painterResource(R.drawable.instagram_logo),
                contentDescription = "Instagram logo",
                modifier = Modifier.size(50.dp)

            )
            Image(
                painter = painterResource(R.drawable.x_logo),
                contentDescription = "Instagram logo",
                modifier = Modifier.size(50.dp)

            )
            Image(
                painter = painterResource(R.drawable.yt_logo),
                contentDescription = "Instagram logo",
                modifier = Modifier.size(50.dp),


            )



        }

    }

}

