package com.PowerZone.PowerZone.Repository

import com.PowerZone.PowerZone.Models.Activity
import com.PowerZone.PowerZone.Models.Participation
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository


@Repository
class ActivityRepository(private val db: JdbcTemplate){

    fun save(activity: Activity) : Activity? {
        db.update("insert into activities (title, description, start_date_time, end_date_time) values (?, ?, ?, ?)",
            activity.title, activity.description, activity.startDateTime, activity.endDateTime)

        return activity
    }


    fun findById(id: String): Activity? = db.query("select * from activities where id = ?", arrayOf(id)) { response, _ ->
        Activity(response.getString("id"), response.getString("title"),
            response.getString("description"),response.getString("start_date_time"),
            response.getString("end_date_time"))
    }.firstOrNull()

    fun findAll(): List<Activity> = db.query("select * from activities") { response, _ ->
        Activity(response.getString("id"), response.getString("title"),
            response.getString("description"),response.getString("start_date_time"),
            response.getString("end_date_time"))
    }

    fun delete(id: String): Activity? {

        val activity = findById(id)

        if (activity != null)
            db.update("delete from activities where id = ?", arrayOf(id))

        return activity

    }







}