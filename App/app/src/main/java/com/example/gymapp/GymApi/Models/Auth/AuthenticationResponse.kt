package com.example.gymapp.GymApi.Models.Auth

data class AuthenticationResponse (
    val accessToken: String,
    val refreshToken: String,
)