package com.example.gymapp.Repositories

import com.example.gymapp.Models.BodyParts
import com.example.gymapp.Models.RetrofitInstance

class BodyPartsRepository {
    private val bodyPartsService = RetrofitInstance.bodyPartsService

    suspend fun getBodyParts(): List<BodyParts> {
        return bodyPartsService.getBodyParts()
    }
}