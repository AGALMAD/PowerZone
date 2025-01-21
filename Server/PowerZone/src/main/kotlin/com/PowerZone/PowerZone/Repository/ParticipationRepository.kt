package com.PowerZone.PowerZone.Repository

import com.PowerZone.PowerZone.Models.Participation
import com.PowerZone.PowerZone.Models.User
import org.springframework.data.repository.CrudRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class ParticipationRepository (private val db: JdbcTemplate){
    fun findAll(userId: String): List<User> = db.query("select * from messages") { response, _ ->
        User(response.getString("id"), response.getString("name"),
            response.getString("email"),response.getString("password"),response.getString("role"))
    }
}