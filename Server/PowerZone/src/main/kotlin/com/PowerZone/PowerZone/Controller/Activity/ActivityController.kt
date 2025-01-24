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
            activityService.findAllActivities()
                    .map { it.toResponse() }

    @PostMapping()
    fun newActivity(@RequestBody activity: ActivityRequest): ActivityResponse =
        activityService.save(activity.toModel()).toResponse()


    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ActivityResponse? =
        activityService.findById(id)?.toResponse()

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String): Unit =
        activityService.deleteById(id)


    private fun Activity.toResponse(): ActivityResponse =
        ActivityResponse(
            id = this.id!!,
            title = this.title,
            description = this.description,
            startDateTime = this.startDateTime,
            endDateTime = this.endDateTime
        )

    private fun ActivityRequest.toModel(): Activity =
        Activity(
            title = this.title,
            description = this.description,
            startDateTime = this.startDateTime,
            endDateTime = this.endDateTime
        )

}