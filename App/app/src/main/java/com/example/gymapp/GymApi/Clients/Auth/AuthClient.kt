package com.example.gymapp.GymApi.Clients.Auth

import com.example.gymapp.GymApi.Models.Auth.AuthenticationRequest
import com.example.gymapp.GymApi.Models.Auth.AuthenticationResponse
import com.example.gymapp.GymApi.Models.Auth.RefreshTokenRequest
import com.example.gymapp.GymApi.Models.Auth.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthClient {

    @POST("/api/auth")
    suspend fun authenticate(@Body authRequest: AuthenticationRequest): Response<AuthenticationResponse>

    @POST("/api/auth/refresh")
    suspend fun refreshAccessToken(@Body tokenRequest: RefreshTokenRequest): Response<TokenResponse>


}