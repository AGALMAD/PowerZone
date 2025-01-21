package com.PowerZone.PowerZone.Repository

import com.PowerZone.PowerZone.Models.Participation
import com.PowerZone.PowerZone.Models.User
import org.springframework.data.repository.CrudRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class ParticipationRepository (private val db: JdbcTemplate){

    fun findAllByUserId(userId: String): List<Participation> = db.query("select * from participation where userId = ?", arrayOf(userId)) { response, _ ->
        Participation(response.getString("userId"), response.getString("activityId"))
    }

    fun newParticipation(participation: Participation) : Participation? {
        db.update("insert into participation(userId, activityId) values (?, ?)",
            participation.userId,participation.activityId)

        return participation
    }

}