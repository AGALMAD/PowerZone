package com.example.gymapp.GymApi.Repositories

import com.example.gymapp.GymApi.Models.Exercise
import com.example.gymapp.GymApi.Models.RetrofitInstance

class ExercisesRepository() {
    private val exercisesService = RetrofitInstance.exercisesService

    suspend fun getExercises(id : Int): List<Exercise> {
        return exercisesService.getAllExercicesByBodyPartId(id)
    }
}