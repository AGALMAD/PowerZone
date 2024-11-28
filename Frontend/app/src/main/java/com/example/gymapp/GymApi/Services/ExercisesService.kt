package com.example.gymapp.GymApi.Services

import com.example.gymapp.GymApi.Models.Exercise
import retrofit2.http.GET
import retrofit2.http.Query

interface ExercisesService {

    @GET("/v3/8b139b11-ca6a-4b87-8fba-3b76befb7bcb")
    suspend fun getAllExercices(): List<Exercise>


}