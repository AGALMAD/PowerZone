package com.PowerZone.PowerZone.Repository

import com.PowerZone.PowerZone.Models.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, String>{
    fun findByEmail(email: String): User?

}