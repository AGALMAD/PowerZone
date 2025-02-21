package com.example.gymapp.GymApi.Models.Activities

data class ActivityRequest(
    val title:String,
    val description:String,
    val startDateTime: String,
    val endDateTime: String
)