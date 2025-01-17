package com.PowerZone.PowerZone.Services

import com.PowerZone.PowerZone.Models.User
import com.PowerZone.PowerZone.Repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(
        private val userRepository: UserRepository,
        private val encoder : PasswordEncoder
) {
    fun createUser(user: User): User? {
        if (userRepository.findByEmail(user.email) != null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists.")
        }
        user.password = encoder.encode(user.password)
        return userRepository.newUser(user)
    }


    fun findAll(): List<User> =
            userRepository.findAll()
                    .toList()

    fun findById(id: String): User? =
            userRepository.findById(id)


    fun findByEmail(email: String): User? =
        userRepository.findByEmail(email)





}