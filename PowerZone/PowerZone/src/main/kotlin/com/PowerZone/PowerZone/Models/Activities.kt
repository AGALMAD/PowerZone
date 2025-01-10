package com.PowerZone.PowerZone.Models

import org.springframework.format.annotation.DateTimeFormat
import java.util.*


data class Activities(val id : UUID, val title : String, val description: String, val startDateTime: DateTimeFormat, val endDateTime: DateTimeFormat ) {
}