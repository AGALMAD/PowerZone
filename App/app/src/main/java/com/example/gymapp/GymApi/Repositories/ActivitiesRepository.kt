package com.example.gymapp.GymApi.Repositories

import com.example.gymapp.GymApi.Models.Activities.ActivityResponse
import com.example.gymapp.GymApi.Models.Activities.ParticipationRequest
import com.example.gymapp.GymApi.Models.Activities.ParticipationResponse
import com.example.gymapp.GymApi.Models.RetrofitApiInstance.activityService

class ActivitiesRepository {
    suspend fun getAllActivities(token:String): List<ActivityResponse>?{
        val response = activityService.getAllActivities("Bearer $token")
        return response.body()
    }

    suspend fun newParticipation(token:String, activityId: String): ParticipationResponse?{
        val response = activityService.newParticipation("Bearer $token", ParticipationRequest(activityId))
        return response.body()
    }

    suspend fun getAllParticipations(token: String): List<ParticipationResponse>?{
        val response = activityService.getAllParticipations("Bearer $token")
        return response.body()
    }

    suspend fun getAllTargetedActivities(token: String): List<ActivityResponse>?{
        val response = activityService.getAllTargetedActivities("Bearer $token")
        return response.body()
    }

    suspend fun delete(token: String,participationId: String): ParticipationResponse?{
        val response = activityService.deleteParticipation("Bearer $token",participationId)
        return response.body()
    }

}