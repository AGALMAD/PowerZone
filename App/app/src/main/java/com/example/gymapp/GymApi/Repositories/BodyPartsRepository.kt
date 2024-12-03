package com.example.gymapp.GymApi.Repositories

import com.example.gymapp.GymApi.Models.BodyPart
import com.example.gymapp.GymApi.Models.RetrofitInstance

class BodyPartsRepository {
    private val bodyPartsService = RetrofitInstance.bodyPartsService

    suspend fun getBodyParts(): List<BodyPart> {
        return bodyPartsService.getBodyParts()
    }
}