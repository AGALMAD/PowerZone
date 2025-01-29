package com.example.gymapp.GymApi.Models.User

data class UserRequest (
    val email: String,
    val name:String,
    var password: String
)