package com.example.gymapp.GymApi.Models.Exercises

class BodyPartsRepository {
    private val bodyPartsService = RetrofitInstance.bodyPartsService

    suspend fun getBodyParts(): List<BodyPart> {
        return bodyPartsService.getBodyParts()
    }
}