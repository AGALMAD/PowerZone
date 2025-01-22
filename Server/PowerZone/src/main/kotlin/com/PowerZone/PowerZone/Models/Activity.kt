package com.PowerZone.PowerZone.Models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("ACTIVITIES")
data class Activity(@Id val id: String = UUID.randomUUID().toString(),
                    val title: String,
                    val description: String,
                    val startDateTime: String,
                    val endDateTime: String
) {



}