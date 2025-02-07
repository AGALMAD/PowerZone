package com.example.gymapp.GymApi.Models.Auth

data class UserRequest (
    val email: String,
    val name:String,
    var password: String
)