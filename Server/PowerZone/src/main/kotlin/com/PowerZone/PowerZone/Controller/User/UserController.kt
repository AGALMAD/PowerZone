package com.PowerZone.PowerZone.Controller.User

import com.PowerZone.PowerZone.Models.Role
import com.PowerZone.PowerZone.Models.User
import com.PowerZone.PowerZone.Services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/api/user")
class UserController(
        private val userService: UserService
) {
    @PostMapping
    fun create(@RequestBody userRequest: UserRequest): UserResponse =
            userService.createUser(userRequest.toModel())
                    ?.toResponse()
                    ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create user.")
    @GetMapping
    fun listAll(): List<UserResponse> =
            userService.findAll()
                    .map { it.toResponse() }
    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): UserResponse =
            userService.findById(id)
                    .map { it.toResponse() }
                    .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.") }


    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String): ResponseEntity<Boolean> {
        val success = userService.deleteById(id)
        return if (success)
            ResponseEntity.noContent()
                    .build()
        else
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
    }

    private fun User.toResponse(): UserResponse =
            UserResponse(
                    id = this.id,
                    email = this.email,
                    name = this.name
            )

    private fun UserRequest.toModel(): User =
            User(
                    id = UUID.randomUUID().toString(),
                    name = this.name,
                    email = this.email,
                    password = this.password,
                    role = Role.USER.toString()
            )
}