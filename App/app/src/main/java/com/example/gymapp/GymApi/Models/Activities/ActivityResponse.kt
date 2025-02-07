package com.example.gymapp.GymApi.Models.Activities

data class ActivityResponse(
    val id: String,
    val title: String,
    val description: String,
    val startDateTime: String,
    val endDateTime: String) {}