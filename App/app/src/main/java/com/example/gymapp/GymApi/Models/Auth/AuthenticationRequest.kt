package com.example.gymapp.GymApi.Models.Auth

data class AuthenticationRequest (
    val email: String,
    val password: String,
)