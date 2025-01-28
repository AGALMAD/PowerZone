package com.example.gymapp.GymApi.Services.Exercises

import com.example.gymapp.GymApi.Models.Exercises.Exercise
import retrofit2.http.GET

interface ExercisesService {

    @GET("b494dbd9-823b-4bc2-ac6b-9158f9eb66c6")
    suspend fun getAllExercices(): List<Exercise>


}