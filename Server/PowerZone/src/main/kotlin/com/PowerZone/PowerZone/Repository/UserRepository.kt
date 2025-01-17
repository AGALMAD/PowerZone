package com.PowerZone.PowerZone.Repository

import com.PowerZone.PowerZone.Models.User
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class UserRepository (
    private val db: JdbcTemplate
){

    fun findAll(): List<User> = db.query("select * from messages") { response, _ ->
        User(response.getString("id"), response.getString("name"),
            response.getString("email"),response.getString("password"),response.getString("role"))
    }

    fun findByEmail(email: String): User? {
        return try {
            db.queryForObject("select * from users where email = ?", arrayOf(email)) { response, _ ->
                User(response.getString("id"), response.getString("name"),
                    response.getString("email"), response.getString("password"), response.getString("role"))
            }
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    fun findById(id: String): User? {
        return try {
            db.queryForObject("select * from users where email = ?", arrayOf(id)) { response, _ ->
                User(response.getString("id"), response.getString("name"),
                    response.getString("email"), response.getString("password"), response.getString("role"))
            }
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }



    fun newUser(user: User) : User? {
        db.update("insert into users(name, email, password) values (?, ?, ?)",
            user.name, user.email, user.password)

        return user
    }


}
