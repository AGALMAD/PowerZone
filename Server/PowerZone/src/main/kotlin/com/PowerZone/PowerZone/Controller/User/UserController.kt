package com.PowerZone.PowerZone.Controller.User

import com.PowerZone.PowerZone.Models.Role
import com.PowerZone.PowerZone.Models.User
import com.PowerZone.PowerZone.Services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.security.Principal
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
    fun getAll(): List<UserResponse> {
        return userService.findAll().map {
            it.toResponse()
        }
    }

    @GetMapping("/{email}")
    fun getByEmail(
            @PathVariable email: String,
            auth: Authentication // Detalles del usuario autenticado
    ): UserResponse
    {
        // Verificamos si el usuario autenticado está intentando acceder a su propio perfil
        if (auth.name != email) {
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "Solo puedes ver tus detalles")
        }

        // Detalles del usuario (no se manda la contraseña)
        return userService.findById(email)
                ?.toResponse()
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
    }

    @DeleteMapping("/{email}")
    fun deleteByEmail(@PathVariable email: String): UserResponse {
        return userService.findById(email)
                ?.toResponse()
                ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Not user found.")

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