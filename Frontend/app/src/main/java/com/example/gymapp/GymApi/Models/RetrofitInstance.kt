package com.example.gymapp.GymApi.Models

import com.example.gymapp.GymApi.Services.BodyPartsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://run.mocky.io/v3/69708275-6dc6-4bb4-a741-6e777627770c/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val bodyPartsService: BodyPartsService by lazy {
        retrofit.create(BodyPartsService::class.java)
    }
}