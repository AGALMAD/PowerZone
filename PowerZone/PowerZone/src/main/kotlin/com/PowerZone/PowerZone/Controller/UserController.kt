package com.PowerZone.PowerZone.Controller

import com.PowerZone.PowerZone.Services.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    fun findByUUID(@PathVariable uuid: UUID): UserResponse =
            userService.findByUUID(uuid)
                    ?.toResponse()
                    ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")


    @DeleteMapping("/{uuid}")
    fun deleteByUUID(@PathVariable uuid: UUID): ResponseEntity<Boolean> {
        val success = userService.deleteByUUID(uuid)

        return if (success)
            ResponseEntity.noContent()
                    .build()
        else
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
    }

    private fun User.toResponse(): UserResponse =
            UserResponse(
                    uuid = this.id,
                    email = this.email,
            )

    private fun UserRequest.toModel(): User =
            User(
                    id = UUID.randomUUID(),
                    email = this.email,
                    password = this.password,
                    role = Role.USER,
            )
}