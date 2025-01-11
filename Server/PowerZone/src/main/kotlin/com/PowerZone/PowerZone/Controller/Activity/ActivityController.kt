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
    fun listAll(): List<ActivityResponse> =
            articleService.findAll()
                    .map { it.toResponse() }

    private fun Activity.toResponse(): ActivityResponse =
            ActivityResponse(
                    id = this.id,
                    title = this.title,
                    description = this.description,
                    startDateTime = this.startDateTime,
                    endDateTime = this.endDateTime
            )
}