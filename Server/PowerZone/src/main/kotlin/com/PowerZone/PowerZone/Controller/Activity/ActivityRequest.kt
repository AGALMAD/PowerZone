package com.PowerZone.PowerZone.Controller.Activity

import java.time.LocalDateTime

data class ActivityRequest(
    val title:String,
    val description:String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime
)