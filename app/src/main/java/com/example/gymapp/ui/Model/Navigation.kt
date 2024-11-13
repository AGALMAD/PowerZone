package com.example.gymapp.ui.Model

import androidx.navigation.NavController

fun NavController.navigateToHome() {
    this.navigate(Routes.Home.route)
}

fun NavController.navigateToAboutUs() {
    this.navigate(Routes.AboutUs.route)
}

fun NavController.navigateToSettings() {
    this.navigate(Routes.Settings.route)
}