package com.PowerZone.PowerZone.Repository

import com.PowerZone.PowerZone.Models.Activity
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
class ActivityRepository {

    private val activities = listOf(
             Activity(
                    UUID.randomUUID(),
                    "Clases de defensa personal",
                    "Clase de defensa personal para niños",
                    LocalDateTime.of(2025, 1, 15, 10, 0),
                    LocalDateTime.of(2025, 1, 15, 11, 0)
             ),
            Activity(
                    UUID.randomUUID(),
                    "Clases de CrossFit",
                    "Clases de CrossFit en grupo para todo tipo de personas",
                    LocalDateTime.of(2025, 1, 15, 10, 0),
                    LocalDateTime.of(2025, 1, 15, 11, 0),
                    )
    )

    fun findAll(): List<Activity> = activities
}