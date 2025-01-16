package com.PowerZone.PowerZone.Services

import com.PowerZone.PowerZone.Models.User
import com.PowerZone.PowerZone.Repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
        private val userRepository: UserRepository,
        private val encoder : PasswordEncoder
) {
    fun createUser(user: User): User? {
        val found = userRepository.findById(user.email)
        return if (found.isEmpty) {
            val userEncode =  user.copy(password = encoder.encode(user.password))
            userRepository.save(userEncode)
            userEncode
        } else null
    }
    fun findAll(): List<User> =
            userRepository.findAll()
                    .toList()

    fun findById(id: String): Optional<User> =
            userRepository.findById(id)


    fun deleteById(id: String): Boolean {
        return if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            true
        }
        else {
            false
        }
    }
}