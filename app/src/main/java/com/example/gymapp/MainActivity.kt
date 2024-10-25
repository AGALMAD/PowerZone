package com.example.gymapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                AboutUs()

            }
        }
    }
}

@Composable
fun AboutUs() {
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
            style = MaterialTheme.typography.titleLarge

        )

        //Imagen Principal
        addImageLogo()

        //Texto sobre nosotros
        Text(
            text = context.getString(R.string.about_us_text),
            textAlign = TextAlign.Justify,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleLarge,

        )


        //Titulo colaboradores
        Text(
            text = context.getString(R.string.collaborators_title),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge

        )

        addImageAle()

        Image(
            painter = painterResource(R.drawable.dani),
            contentDescription = null
        )
        addImageDani()


    }

}


@Composable
fun addImageLogo() {

    val image = painterResource(R.drawable.logo_sin_fondo)

    Image(
        painter = image,
        contentDescription = null
    )

}

@Composable
fun addImageAle() {

    val image = painterResource(R.drawable.foto_perfil)

    Image(
        painter = image,
        contentDescription = null
    )

}


@Composable
fun addImageDani() {

    val image = painterResource(R.drawable.dani)

    Image(
        painter = image,
        contentDescription = null
    )

}

