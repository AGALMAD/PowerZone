package com.example.gymapp.GymApi.Services.Auth

import com.example.gymapp.GymApi.Models.Auth.UserRequest
import com.example.gymapp.GymApi.Models.Auth.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @POST("/api/user")
    suspend fun create(@Body userRequest: UserRequest): Response<UserResponse>

    //Método solo disponible con token
    @GET("/api/user")
    suspend fun getAuthUser(@Header("Authorization") token: String): Response<UserResponse>


    //Métodos solo disponibles para usuarios administradores
    @GET("/api/user/{email}")
    suspend fun getByEmail(@Path("email") email: String): Response<UserResponse>

    @GET("/api/user/all")
    suspend fun getAll(): Response<UserResponse>


}