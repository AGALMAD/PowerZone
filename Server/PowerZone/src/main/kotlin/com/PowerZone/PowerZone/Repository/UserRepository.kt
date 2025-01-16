package com.PowerZone.PowerZone.Repository

import com.PowerZone.PowerZone.Models.Activity
import com.PowerZone.PowerZone.Models.Role
import com.PowerZone.PowerZone.Models.User
import org.springframework.data.repository.CrudRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<User, String>{
    fun findByEmail(email: String): User?

    fun findByUUID(UUID: String): User?
    fun deleteByUUID(UUID: String): Boolean





}