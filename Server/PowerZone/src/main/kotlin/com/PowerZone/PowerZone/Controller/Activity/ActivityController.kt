package com.PowerZone.PowerZone.Controller.Activity

import com.PowerZone.PowerZone.Models.Activity
import com.PowerZone.PowerZone.Services.ActivityService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/activity")
class ArticleController(
        private val articleService: ActivityService
) {
    @GetMapping()
    fun getAll(): List<ActivityResponse> =
            articleService.allActivities()
                    .map { it.toResponse()!! }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ActivityResponse? =
        articleService.getActivityById(id)?.toResponse()

    private fun Activity.toResponse(): ActivityResponse? =
        this.id?.let {
            ActivityResponse(
                id = it,
                title = this.title,
                description = this.description,
                startDateTime = this.startDateTime,
                endDateTime = this.endDateTime
            )
        }
}