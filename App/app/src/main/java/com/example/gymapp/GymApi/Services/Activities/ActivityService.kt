package com.example.gymapp.GymApi.Services.Activities

import com.example.gymapp.GymApi.Models.Activities.ActivityResponse
import com.example.gymapp.GymApi.Models.Activities.Participation
import com.example.gymapp.GymApi.Models.Auth.AuthenticationRequest
import com.example.gymapp.GymApi.Models.Auth.AuthenticationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ActivityService {


    @POST("/api/participations")
    suspend fun newParticipation(@Path("participationId") participationId: String): Response<Participation>


    @GET("/api/participations")
    suspend fun getAllParticipations(@Header("Authorization") token: String): Response<List<Participation>>


    @POST("/api/participations/details")
    suspend fun getAllTargetedActivities(@Header("Authorization") token: String): Response<List<ActivityResponse>>


    @DELETE("/api/participations")
    suspend fun authenticate(@Path("participationId") participationId: String): Response<Participation>

}