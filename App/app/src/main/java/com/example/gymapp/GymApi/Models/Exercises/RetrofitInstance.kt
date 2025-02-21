package com.example.gymapp.GymApi.Models.Exercises

import com.example.gymapp.GymApi.Clients.Exercises.BodyPartsClient
import com.example.gymapp.GymApi.Clients.Exercises.ExercisesClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://run.mocky.io/v3/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val bodyPartsService: BodyPartsClient by lazy {
        retrofit.create(BodyPartsClient::class.java)
    }

    val exercisesService: ExercisesClient by lazy {
        retrofit.create(ExercisesClient::class.java)
    }
}