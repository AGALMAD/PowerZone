package com.example.gymapp.GymApi.Services

import com.example.gymapp.GymApi.Models.BodyPart
import retrofit2.http.GET

interface BodyPartsService {
    @GET("bodyParts")
    suspend fun getBodyParts(): List<BodyPart>
}