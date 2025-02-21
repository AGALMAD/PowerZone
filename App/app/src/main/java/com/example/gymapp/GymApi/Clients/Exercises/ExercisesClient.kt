package com.example.gymapp.GymApi.Clients.Exercises

import com.example.gymapp.GymApi.Models.Exercises.Exercise
import retrofit2.http.GET

interface ExercisesClient {

    @GET("8e5e7f39-73f6-42fb-b102-ad0410fbea34")
    suspend fun getAllExercices(): List<Exercise>


}