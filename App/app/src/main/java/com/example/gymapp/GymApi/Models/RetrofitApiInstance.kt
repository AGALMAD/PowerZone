package com.example.gymapp.GymApi.Models

import com.example.gymapp.GymApi.Clients.Activities.ActivityClient
import com.example.gymapp.GymApi.Clients.Auth.AuthClient
import com.example.gymapp.GymApi.Clients.Auth.UserClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApiInstance {

    private const val BASE_URL = "http://10.0.2.2:8080"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(RetrofitApiInstance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authService: AuthClient by lazy {
        retrofit.create(AuthClient::class.java)
    }

    val userService: UserClient by lazy {
        retrofit.create(UserClient::class.java)
    }


    val activityService: ActivityClient by lazy {
        retrofit.create(ActivityClient::class.java)
    }


}