package com.example.gymapp.Services

import com.example.gymapp.Models.BodyParts
import retrofit2.http.GET

interface BodyPartsService {
    @GET("bodyParts")
    suspend fun getBodyParts(): List<BodyParts>
}