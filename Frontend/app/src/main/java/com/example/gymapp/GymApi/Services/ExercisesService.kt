package com.example.gymapp.GymApi.Services

import com.example.gymapp.GymApi.Models.Exercise
import retrofit2.http.GET

interface ExercisesService {
    @GET("8b139b11-ca6a-4b87-8fba-3b76befb7bcb")
    suspend fun getExercisesByBodyPartId(): List<Exercise>
}