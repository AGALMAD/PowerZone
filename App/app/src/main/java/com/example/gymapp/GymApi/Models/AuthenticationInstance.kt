package com.example.gymapp.GymApi.Models

import com.example.gymapp.GymApi.Models.Exercises.RetrofitInstance
import com.example.gymapp.GymApi.Services.Auth.AuthService
import com.example.gymapp.GymApi.Services.Auth.UserService
import com.example.gymapp.GymApi.Services.Exercises.BodyPartsService
import com.example.gymapp.GymApi.Services.Exercises.ExercisesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthenticationInstance {

    private const val BASE_URL = "http://10.0.2.2:8080"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AuthenticationInstance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }

    val userService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }
}