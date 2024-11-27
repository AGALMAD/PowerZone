package com.example.gymapp.GymApi.Services

import com.example.gymapp.GymApi.Models.BodyPart
import retrofit2.http.GET

interface BodyPartsService {

    @GET("69708275-6dc6-4bb4-a741-6e777627770c/")
    suspend fun getBodyParts(): List<BodyParts>

}