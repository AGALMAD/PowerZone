package com.PowerZone.PowerZone.Models

import java.time.LocalDateTime
import java.util.*


data class Activity(val id: UUID, val title: String, val description: String, val startDateTime: LocalDateTime, val endDateTime: LocalDateTime) {



}