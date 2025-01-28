package com.example.gymapp.GymApi.Models.Exercises

data class Exercise (
    val id: String,
    val title: String,
    val bodyPartsId: List<Int>,
    val imageUrl: String,
    val videoUrl: String
)

