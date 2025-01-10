package com.PowerZone.PowerZone.Models

import java.util.UUID

data class User(val id: UUID, val name: String, val email: String, val password: String, val role: Role) {
}

enum class Role {
    USER, ADMIN
}