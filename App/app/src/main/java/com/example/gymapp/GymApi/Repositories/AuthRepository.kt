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

    suspend fun doRefreshAccessToken(token:String):TokenResponse{
        return withContext(Dispatchers.IO){
            val response = authService.refreshAccessToken(RefreshTokenRequest(
                token = token
            ))
            TokenResponse(response.body()?.token ?:"")
        }
    }

    suspend fun getAuthUser(token:String): UserResponse{
        return withContext(Dispatchers.IO){
            val response = userService.getAuthUser("Bearer $token")
            UserResponse(
                response.body()?.id ?:"",
                response.body()?.name ?:"",
                response.body()?.email ?:"")
        }
    }

    suspend fun login(email:String, password:String):AuthenticationResponse {
        return withContext(Dispatchers.IO){
            val response = authService.authenticate(AuthenticationRequest(
                email = email,
                password = password
            ))
            AuthenticationResponse(
                response.body()?.accessToken?:"",
                response.body()?.refreshToken?:"")
        }
    }

    suspend fun signUp(name:String, email:String, password:String):UserResponse{

        return withContext(Dispatchers.IO){
            val response = userService.create(UserRequest(
                name = name,
                email = email,
                password = password
            ))
            UserResponse(
                response.body()?.id?:"",
                response.body()?.name?:"",
                response.body()?.email?:"")
        }
    }

}