package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.font.FontWeight
import com.example.gymapp.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val bodyFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("ABeeZee"),
        fontProvider = provider,
    )
)

val displayFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Abril Fatface"),
        fontProvider = provider,
    )
)

// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    // Tamaño para Títulos
    displayLarge = TextStyle(
        fontFamily = displayFontFamily,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
    ),
    // Tamaño Subtítulos
    headlineSmall = TextStyle(
        fontFamily = displayFontFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    ),

    // Tamaño para textos
    bodyLarge = TextStyle(
        fontFamily = bodyFontFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal
    ),

    bodyMedium = TextStyle(
        fontFamily = bodyFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),

    bodySmall = TextStyle(
        fontFamily = bodyFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    )
)

