package com.PowerZone.PowerZone.Repository

import com.PowerZone.PowerZone.Models.Participation
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class ParticipationRepository (private val db: JdbcTemplate){

    fun findAllByUserId(userId: String): List<Participation> = db.query("select * from participation where userId = ?", arrayOf(userId)) { response, _ ->
        Participation(response.getString("userId"), response.getString("activityId"))
    }

    fun findParticipation(participation: Participation): List<Participation> = db.query(
        "select * from participation where userId = ? and activityId = ?",
        arrayOf(participation.userId, participation.activityId)
    ) { response, _ ->
        Participation(response.getString("userId"), response.getString("activityId"))
    }


    fun save(participation: Participation) : Participation? {
        db.update("insert into participation(userId, activityId) values (?, ?)",
            participation.userId,participation.activityId)

        return participation
    }

    fun deleteParticipation(participation: Participation): Participation? {
        db.update("delete from participation where userId = ? and activityId = ?",
            participation.userId, participation.activityId)

        return participation
    }


}