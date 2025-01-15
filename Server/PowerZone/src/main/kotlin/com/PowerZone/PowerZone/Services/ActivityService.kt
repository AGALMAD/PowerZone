package com.PowerZone.PowerZone.Services

import com.PowerZone.PowerZone.Models.Activity
import com.PowerZone.PowerZone.Repository.ActivityRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ActivityService(private val activityRepository : ActivityRepository) {

    fun allActivities(): List<Activity> = activityRepository.findAll().toList()

    fun getActivityById(id:String): Activity? = activityRepository.findByIdOrNull(id)

    fun newActivity(activity: Activity) : Activity = activityRepository.save(activity)
}