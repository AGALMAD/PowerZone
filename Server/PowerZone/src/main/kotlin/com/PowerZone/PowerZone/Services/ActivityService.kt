package com.PowerZone.PowerZone.Services

import com.PowerZone.PowerZone.Models.Activity
import com.PowerZone.PowerZone.Repository.ActivityRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ActivityService(private val activityRepository : ActivityRepository) {

    fun findAllActivities(): List<Activity> = activityRepository.findAll().toList()

    fun findById(id:String): Activity? = activityRepository.findByIdOrNull(id)

    fun save(activity: Activity) : Activity = activityRepository.save(activity)

    fun deleteById(id: String) : Unit = activityRepository.deleteById(id)


}