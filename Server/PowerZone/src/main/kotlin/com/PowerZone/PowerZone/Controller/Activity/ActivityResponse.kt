package com.PowerZone.PowerZone.Controller.Activity

import java.time.LocalDateTime
import java.util.*

data class ActivityResponse(val id: UUID, val title: String, val description: String, val startDateTime: LocalDateTime, val endDateTime: LocalDateTime) {
}