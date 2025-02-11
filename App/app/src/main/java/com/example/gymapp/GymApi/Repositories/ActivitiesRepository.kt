package com.example.gymapp.GymApi.Repositories

import com.example.gymapp.GymApi.Models.Activities.ActivityResponse
import com.example.gymapp.GymApi.Models.Activities.Participation
import com.example.gymapp.GymApi.Models.RetrofitApiInstance.activityService

class ActivitiesRepository {
    suspend fun getAllActivities(token:String): List<ActivityResponse>?{
        val response = activityService.getAllActivities("Bearer $token")
        return response.body()
    }

    suspend fun newParticipation(token:String, activityId: String): Participation?{
        val response = activityService.newParticipation("Bearer $token",activityId)
        return response.body()
    }

    suspend fun getAllParticipations(token: String): List<Participation>?{
        val response = activityService.getAllParticipations("Bearer $token")
        return response.body()
    }

    suspend fun getAllTargetedActivities(token: String): List<ActivityResponse>?{
        val response = activityService.getAllTargetedActivities("Bearer $token")
        return response.body()
    }

    suspend fun authenticate(token: String,participationId: String): Participation?{
        val response = activityService.authenticate("Bearer $token",participationId)
        return response.body()
    }

}