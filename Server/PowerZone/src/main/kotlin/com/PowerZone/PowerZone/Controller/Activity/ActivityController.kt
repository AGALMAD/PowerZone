package com.PowerZone.PowerZone.Controller.Activity

import com.PowerZone.PowerZone.Models.Activity
import com.PowerZone.PowerZone.Services.ActivityService
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/activities")
class ActivityController(
        private val articleService: ActivityService
) {
    @GetMapping()
    fun getAll(): List<ActivityResponse> =
            articleService.allActivities()
                    .map { it.toResponse() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ActivityResponse? =
        articleService.getActivityById(id)?.toResponse()

    private fun Activity.toResponse(): ActivityResponse =
        ActivityResponse(
            id = this.id,
            title = this.title,
            description = this.description,
            startDateTime = this.startDateTime,
            endDateTime = this.endDateTime
        )
}