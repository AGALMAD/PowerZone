package com.example.gymapp.GymApi.Services

import com.example.gymapp.GymApi.Models.Exercise
import retrofit2.http.GET
import retrofit2.http.Query

interface ExercisesService {

    @GET("v3/4440a7ef-81a5-44c6-bcff-0ef2d73dd866")
    suspend fun getAllExercices(): List<Exercise>


}