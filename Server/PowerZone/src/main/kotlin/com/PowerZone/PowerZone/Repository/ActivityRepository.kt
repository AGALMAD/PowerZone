package com.PowerZone.PowerZone.Repository

import com.PowerZone.PowerZone.Models.Activity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface ActivityRepository : CrudRepository<Activity, String>