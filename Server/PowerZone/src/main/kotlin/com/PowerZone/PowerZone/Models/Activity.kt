package com.PowerZone.PowerZone.Models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("ACTIVITY")
data class Activity(@Id val id : String?,
                    val title: String,
                    val description: String,
                    val startDateTime: LocalDateTime,
                    val endDateTime: LocalDateTime) {



}