package com.example.gymapp.GymApi.Services

import com.example.gymapp.GymApi.Models.BodyParts
import retrofit2.http.GET

interface BodyPartsService {
    @GET("bodyParts")
    suspend fun getBodyParts(): List<BodyParts>
}