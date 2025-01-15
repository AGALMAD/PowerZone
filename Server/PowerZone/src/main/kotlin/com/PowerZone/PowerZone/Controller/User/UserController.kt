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
    @GetMapping("/{uuid}")
    fun findByUUID(@PathVariable uuid: String): UserResponse =
            userService.findByUUID(uuid)
                    ?.toResponse()
                    ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
    @DeleteMapping("/{uuid}")
    fun deleteByUUID(@PathVariable uuid: String): ResponseEntity<Boolean> {
        val success = userService.deleteByUUID(uuid)
        return if (success)
            ResponseEntity.noContent()
                    .build()
        else
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
    }

    private fun User.toResponse(): UserResponse =
            UserResponse(
                    uuid = UUID.fromString(id),
                    email = this.email,
                    name = this.name
            )

    private fun UserRequest.toModel(): User =
            User(
                    id = UUID.randomUUID().toString(),
                    name = this.name,
                    email = this.email,
                    password = this.password,
            )
}