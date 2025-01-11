package com.PowerZone.PowerZone.Models

import java.util.UUID

data class User(val id: UUID = UUID.randomUUID(), val name: String, val email: String, val password: String, val role: Role = Role.USER) {

}

enum class Role {
    USER, ADMIN
}