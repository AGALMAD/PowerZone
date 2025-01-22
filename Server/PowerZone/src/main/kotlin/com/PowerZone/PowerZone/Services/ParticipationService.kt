package com.PowerZone.PowerZone.Services

import com.PowerZone.PowerZone.Models.Activity
import com.PowerZone.PowerZone.Models.Participation
import com.PowerZone.PowerZone.Repository.ActivityRepository
import com.PowerZone.PowerZone.Repository.ParticipationRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ParticipationService(private val participationRepository: ParticipationRepository,
    private val activityRepository: ActivityRepository
) {
    fun findAllByUserId(userId: String): List<Optional<Activity>> {
        val participations = participationRepository.findAllByUserId(userId)
        val activities = participations.map {
            activityRepository.findById(it.activityId)
        }

        return activities
    }


    fun notExistParticipation(participation: Participation): Boolean {
        val activity = participationRepository.findParticipation(participation)
        return activity.isEmpty()
    }

    fun newParticipation(participation: Participation) : Participation? =
        participationRepository.save(participation)

    fun deleteParticipation(participation: Participation) : Participation? =
        participationRepository.deleteParticipation(participation)
}