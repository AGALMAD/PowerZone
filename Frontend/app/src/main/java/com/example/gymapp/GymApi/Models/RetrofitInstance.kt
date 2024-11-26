package com.example.gymapp.GymApi.Models

import com.example.gymapp.GymApi.Services.BodyPartsService
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

    val bodyPartsService: BodyPartsService by lazy {
        retrofit.create(BodyPartsService::class.java)
    }
}