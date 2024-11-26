package com.example.gymapp.GymApi.Repositories

import com.example.gymapp.GymApi.Models.BodyParts
import com.example.gymapp.GymApi.Models.RetrofitInstance

class BodyPartsRepository {
    private val bodyPartsService = RetrofitInstance.bodyPartsService

    suspend fun getBodyParts(): List<BodyParts> {
        return bodyPartsService.getBodyParts()
    }
}