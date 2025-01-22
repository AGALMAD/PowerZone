package com.PowerZone.PowerZone.Models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("ACTIVITIES")
data class Activity(@Id val id: String? = null,
                    val title: String,
                    val description: String,
                    val startDateTime: String,
                    val endDateTime: String
) {



}