package com.example.gymapp.GymApi.Services

import com.example.gymapp.GymApi.Models.Exercise
import retrofit2.http.GET
import retrofit2.http.Query

interface ExercisesService {

    @GET("/v3/8b139b11-ca6a-4b87-8fba-3b76befb7bcb")
    suspend fun getAllExercices(): List<Exercise>

    @GET("/v3/8b139b11-ca6a-4b87-8fba-3b76befb7bcb")
    suspend fun getAllExercicesByBodyPartId(id: Int): List<Exercise> {
        // Obtener todos los ejercicios
        val allExercices = getAllExercices()

        // Filtrar los ejercicios que tengan un bodyPartId que esté en el arreglo bodyPartsId
        return allExercices.filter { id in it.bodyPartsId }
    }


}