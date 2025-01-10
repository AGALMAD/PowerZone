package com.PowerZone.PowerZone.Repository

import com.PowerZone.PowerZone.Models.Role
import com.PowerZone.PowerZone.Models.User
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepository {
    private val users = mutableSetOf(
            User(
                    id = UUID.randomUUID(),
                    name = "pepe",
                    email = "pepe@gmail.com",
                    password = "pass1",
                    role = Role.USER,
            ),
            User(
                    id = UUID.randomUUID(),
                    name = "paco",
                    email = "paco@gmail.com",
                    password = "pass2",
                    role = Role.ADMIN,
            ),
            User(
                    id = UUID.randomUUID(),
                    name = "manolo",
                    email = "manolo@gmail.com",
                    password = "pass3",
                    role = Role.USER,
            ),
    )
    fun save(user: User): Boolean =
            users.add(user)
    fun findByEmail(email: String): User? =
            users
                    .firstOrNull { it.email == email }
    fun findAll(): Set<User> =
            users
    fun findByUUID(uuid: UUID): User? =
            users
                    .firstOrNull { it.id == uuid }
    fun deleteByUUID(uuid: UUID): Boolean {
        val foundUser = findByUUID(uuid)
        return foundUser?.let {
            users.removeIf {
                it.id == uuid
            }
        } ?: false
    }
}