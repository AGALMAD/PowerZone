package com.example.gymapp.Appearance.Views.Dialog

import com.example.gymapp.R

object ToastMessage {
    fun getStringResourceId(errorType: AuthErrorType): Int {
        return when (errorType) {
            // Según el error le pasa su texto
            AuthErrorType.INVALID_CREDENTIALS -> R.string.error_invalid_credentials
            AuthErrorType.NETWORK_ERROR -> R.string.error_network
            AuthErrorType.UNKNOWN_ERROR -> R.string.error_unknown
        }
    }
}