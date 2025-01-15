package com.PowerZone.PowerZone.Models

import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("PARTICIPATION")
data class Participation(val userId: String, val activityId: String) {}