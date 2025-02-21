package com.example.gymapp.GymApi.Clients.Exercises

import com.example.gymapp.GymApi.Models.Exercises.BodyPart
import retrofit2.http.GET

interface BodyPartsClient {

    @GET("d6836a9a-acdc-47dd-a198-1b5c9d320c87\t")
    suspend fun getBodyParts(): List<BodyPart>

}