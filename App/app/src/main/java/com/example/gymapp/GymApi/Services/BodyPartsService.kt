package com.example.gymapp.GymApi.Services

import com.example.gymapp.GymApi.Models.BodyPart
import retrofit2.http.GET

interface BodyPartsService {

    @GET("b92c6c4f-f568-4df3-af1f-cc2ef88dbd02")
    suspend fun getBodyParts(): List<BodyPart>

}