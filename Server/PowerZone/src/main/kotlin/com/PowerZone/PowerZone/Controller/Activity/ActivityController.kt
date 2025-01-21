package com.PowerZone.PowerZone.Controller.Activity

import com.PowerZone.PowerZone.Models.Activity
import com.PowerZone.PowerZone.Services.ActivityService
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/activities")
class ActivityController(
        private val activityService: ActivityService
) {
    @GetMapping()
    fun getAll(): List<ActivityResponse> =
            activityService.allActivities()
                    .map { it.toResponse() }

    @PostMapping()
    fun newActivity(@RequestBody activity: ActivityRequest): Activity? =
        activityService.newActivity(activity.toModel())


    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ActivityResponse? =
        activityService.findById(id)?.toResponse()

    private fun Activity.toResponse(): ActivityResponse =
        ActivityResponse(
            id = this.id,
            title = this.title,
            description = this.description,
            startDateTime = this.startDateTime,
            endDateTime = this.endDateTime
        )

    private fun ActivityRequest.toModel(): Activity =
        Activity(
            id = UUID.randomUUID().toString(),
            title = this.title,
            description = this.description,
            startDateTime = this.startDateTime,
            endDateTime = this.endDateTime
        )

}