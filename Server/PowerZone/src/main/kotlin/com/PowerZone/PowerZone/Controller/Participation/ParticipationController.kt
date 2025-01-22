package com.PowerZone.PowerZone.Controller.Participation

import com.PowerZone.PowerZone.Controller.User.UserRequest
import com.PowerZone.PowerZone.Controller.User.UserResponse
import com.PowerZone.PowerZone.Models.Activity
import com.PowerZone.PowerZone.Models.Participation
import com.PowerZone.PowerZone.Services.ParticipationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/participations")
class ParticipationController(
    private val participationService: ParticipationService
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
    fun getByUserId(@PathVariable userId: String): List<Activity?> =
        participationService.findAllByUserId(userId)

    @DeleteMapping
    fun delete(@RequestBody participation: Participation): Participation =
        participationService.deleteParticipation(participation)
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot delete participation.")


}