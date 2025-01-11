package com.PowerZone.PowerZone.Services

import com.PowerZone.PowerZone.Models.Activity
import com.PowerZone.PowerZone.Repository.ActivityRepository
import org.springframework.stereotype.Service

@Service
class ActivityService(private val activityRepository : ActivityRepository) {

    fun findAll(): List<Activity> =
            activityRepository.findAll()

}