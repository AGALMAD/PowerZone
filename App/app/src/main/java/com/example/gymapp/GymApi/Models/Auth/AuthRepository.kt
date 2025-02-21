package com.example.gymapp.GymApi.Models.Auth
import com.example.gymapp.GymApi.Models.RetrofitApiInstance.authService
import com.example.gymapp.GymApi.Models.RetrofitApiInstance.userService

class AuthRepository() {

    suspend fun doRefreshAccessToken(token:String):TokenResponse?{
        val response = authService.refreshAccessToken(RefreshTokenRequest(
            token = token
        ))

        return response.body()
    }

    suspend fun getAuthUser(token:String): UserResponse?{
        val response = userService.getAuthUser("Bearer $token")

        return response.body()
    }

    suspend fun login(email: String, password: String): AuthenticationResponse? {
        val response = authService.authenticate(
            AuthenticationRequest(
                email = email,
                password = password
            )
        )

        return response.body()
    }


    suspend fun signUp(name:String, email:String, password:String): UserResponse?{

        val response = userService.create(
            UserRequest(
            name = name,
            email = email,
            password = password
        )
        )

        return response.body()
    }

}