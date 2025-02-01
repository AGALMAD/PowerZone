package com.example.gymapp.GymApi.Repositories
import com.example.gymapp.GymApi.Models.Auth.AuthenticationRequest
import com.example.gymapp.GymApi.Models.Auth.AuthenticationResponse
import com.example.gymapp.GymApi.Models.Auth.RefreshTokenRequest
import com.example.gymapp.GymApi.Models.Auth.TokenResponse
import com.example.gymapp.GymApi.Models.AuthenticationInstance.authService
import com.example.gymapp.GymApi.Models.AuthenticationInstance.userService
import com.example.gymapp.GymApi.Models.Exercises.RetrofitInstance
import com.example.gymapp.GymApi.Models.User.UserRequest
import com.example.gymapp.GymApi.Models.User.UserResponse
import com.example.gymapp.GymApi.Services.Auth.AuthService
import com.example.gymapp.GymApi.Services.Auth.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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


    suspend fun signUp(name:String, email:String, password:String):UserResponse?{

        val response = userService.create(UserRequest(
            name = name,
            email = email,
            password = password
        ))

        return response.body()
    }

}