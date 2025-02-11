package com.PowerZone.PowerZone.Controller.Participation

import com.PowerZone.PowerZone.Controller.User.UserResponse
import com.PowerZone.PowerZone.Models.Activity
import com.PowerZone.PowerZone.Models.Participation
import com.PowerZone.PowerZone.Models.User
import com.PowerZone.PowerZone.Services.ParticipationService
import com.PowerZone.PowerZone.Services.UserService
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/api/participations")
class ParticipationController(
    private val participationService: ParticipationService,
    private val userService: UserService
) {

    @PostMapping
    fun create(@RequestBody participation: Participation): Participation =
        if (participationService.notExistParticipation(participation)) {
            participationService.newParticipation(participation)
                ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create participation.")
        } else {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "participation already created.")
        }

    @GetMapping("/{userId}")
    fun getAllParticipationsByUserId(@PathVariable userId: String): List<Participation> =
            participationService.findAllParticipationsByUserId(userId)

    @GetMapping("/details")
    fun getAllTargetedActivities(auth: Authentication): List<Optional<Activity>>? =
        userService.findByEmail(auth.name)?.id?.let { participationService.findAllActivitiesByUserId(it) }

    @DeleteMapping("/{activityId}")
    fun delete(
        auth: Authentication,
        @PathVariable activityId: String): Participation =
        userService.findByEmail(auth.name)?.id?.let {
            participationService.deleteParticipation(Participation(it,activityId)) }
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot delete participation.")

}