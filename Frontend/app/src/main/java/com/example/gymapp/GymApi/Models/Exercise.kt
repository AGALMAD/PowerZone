package com.example.gymapp.GymApi.Models

data class Exercise (
    val id: String,
    val title: String,
    val bodyPartsId: Array<String>,
    val imageUrl: String,
    val videoUrl: String
)