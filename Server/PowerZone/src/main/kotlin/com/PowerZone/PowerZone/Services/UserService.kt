package com.PowerZone.PowerZone.Services

import com.PowerZone.PowerZone.Models.User
import com.PowerZone.PowerZone.Repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
        private val userRepository: UserRepository
) {
    fun createUser(user: User): User? {
        val found = userRepository.findByEmail(user.email)
        return if (found == null) {
            userRepository.save(user)
            user
        } else null
    }
    fun findByUUID(uuid: String): User? =
            userRepository.findByUUID(uuid)
    fun findAll(): List<User> =
            userRepository.findAll()
                    .toList()
    fun deleteByUUID(uuid: String): Boolean =
            userRepository.deleteByUUID(uuid)
}