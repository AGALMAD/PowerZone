package com.PowerZone.PowerZone.Services

import com.PowerZone.PowerZone.Models.User
import com.PowerZone.PowerZone.Repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class UserService(
        private val userRepository: UserRepository,
        private val encoder : PasswordEncoder
) {
    fun createUser(user: User): User? {
        if (userRepository.findByEmail(user.email) != null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists.")
        }
        val userEncode = user.copy(password = encoder.encode(user.password))
        return userRepository.save(userEncode)
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