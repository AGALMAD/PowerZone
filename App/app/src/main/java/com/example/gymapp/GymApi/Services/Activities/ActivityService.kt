package com.example.gymapp.GymApi.Services.Activities

import com.example.gymapp.GymApi.Models.Activities.ActivityResponse
import com.example.gymapp.GymApi.Models.Activities.ParticipationRequest
import com.example.gymapp.GymApi.Models.Activities.ParticipationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ActivityService {

    @GET("/api/activities")
    suspend fun getAllActivities(@Header("Authorization") token: String): Response<List<ActivityResponse>>


    @POST("/api/participations")
    suspend fun newParticipation(@Header("Authorization") token: String, @Body participation: ParticipationRequest): Response<ParticipationResponse>


    @GET("/api/participations")
    suspend fun getAllParticipations(@Header("Authorization") token: String): Response<List<ParticipationResponse>>


    @GET("/api/participations/details")
    suspend fun getAllTargetedActivities(@Header("Authorization") token: String): Response<List<ActivityResponse>>


    @DELETE("/api/participations/")
    suspend fun deleteParticipation(@Header("Authorization") token: String, @Path("activityId") activityId: String): Response<ParticipationResponse>

}