package com.example.gymapp.GymApi.Services.Auth

import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.gymapp.GymApi.Models.Auth.AuthenticationRequest
import com.example.gymapp.GymApi.Models.Auth.AuthenticationResponse
import com.example.gymapp.GymApi.Models.User.UserRequest
import com.example.gymapp.GymApi.Models.User.UserResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @POST("/api/user")
    suspend fun create(userRequest: UserRequest): UserResponse

    //Método solo disponible con token
    @GET("/api/user")
    suspend fun getAuthUser(): UserResponse

    //Métodos solo disponibles para usuarios administradores
    @GET("/api/user/{email}")
    suspend fun getByEmail(@Path("email") email: String): UserResponse

    @GET("/api/user/all")
    suspend fun getAll(): UserResponse

}