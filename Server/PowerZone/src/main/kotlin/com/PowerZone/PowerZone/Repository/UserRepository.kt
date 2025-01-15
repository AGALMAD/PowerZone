package com.PowerZone.PowerZone.Repository

import com.PowerZone.PowerZone.Models.Role
import com.PowerZone.PowerZone.Models.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepository(
        private val encoder: PasswordEncoder
) {
    private val users = mutableSetOf(
            User(
                id = UUID.randomUUID().toString(),
                name = "alejandro",
                email = "ale@gmail.com",
                password = "pass1",
                role = Role.ADMIN.toString()
            ),
            User(
                    id = UUID.randomUUID().toString(),
                    name = "pepe",
                    email = "pepe@gmail.com",
                    password = "pass1",
            ),
            User(
                id = UUID.randomUUID().toString(),
                    name = "paco",
                    email = "paco@gmail.com",
                    password = "pass2",
            ),
            User(
                id = UUID.randomUUID().toString(),
                    name = "manolo",
                    email = "manolo@gmail.com",
                    password = "pass3",
            ),
    )

    //Se guardan los usuarios con la contraseña encriptada
    fun save(user: User): Boolean {
        val updated = user.copy(password = encoder.encode(user.password))
        return users.add(updated)
    }


    fun findByEmail(email: String): User? =
            users
                    .firstOrNull { it.email == email }

    fun findAll(): Set<User> =
            users

    fun findByUUID(uuid: String): User? =
            users.firstOrNull { it.id == uuid }

    fun deleteByUUID(uuid: String): Boolean {
        val foundUser = findByUUID(uuid)

        return foundUser?.let {
            users.removeIf {
                it.id == uuid
            }
        } ?: false
    }

}