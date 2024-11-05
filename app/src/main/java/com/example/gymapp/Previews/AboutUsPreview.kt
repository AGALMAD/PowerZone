package com.example.gymapp.Previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme
import com.example.gymapp.AboutUsContent

@Preview(
    showBackground = true
)
@Composable
fun AboutUsPreview() {
    AppTheme {
        AboutUsContent()
    }
}