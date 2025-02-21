package com.example.gymapp.GymApi.Models.Exercises

class ExercisesRepository() {
    private val exercisesService = RetrofitInstance.exercisesService

    suspend fun getExercises(id : Int): List<Exercise> {

        // Obtener todos los ejercicios
        val allExercices = exercisesService.getAllExercices()

        // Filtrar los ejercicios que tengan un bodyPartId que esté en el arreglo bodyPartsId
        return allExercices.filter { id in it.bodyPartsId }
    }
}