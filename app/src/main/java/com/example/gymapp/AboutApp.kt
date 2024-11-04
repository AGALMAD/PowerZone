package com.example.gymapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.compose.AppTheme

class AboutApp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                AboutAppContent()
            }
        }
    }
}

@Composable
fun AboutAppContent() {
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
        //Titulo sobre la app
        Text(
            text = context.getString(R.string.about_app_title),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(30.dp)


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