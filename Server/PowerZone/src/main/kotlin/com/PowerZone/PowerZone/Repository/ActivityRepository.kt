package com.PowerZone.PowerZone.Repository

import com.PowerZone.PowerZone.Models.Activity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface ActivityRepository : CrudRepository<Activity, String>