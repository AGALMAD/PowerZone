package com.PowerZone.PowerZone.Models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("USERS")
data class User(@Id val id: String,
                val name: String,
                val email: String,
                var password: String,
                val role: String = Role.USER.toString()) {

}

enum class Role {
    USER,
    ADMIN
}