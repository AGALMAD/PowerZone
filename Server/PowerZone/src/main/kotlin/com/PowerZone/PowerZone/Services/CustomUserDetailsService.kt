package com.PowerZone.PowerZone.Services

import com.PowerZone.PowerZone.Repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User




typealias ApplicationUser = com.PowerZone.PowerZone.Models.User

@Service
class CustomUserDetailsService(
        private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
            userRepository.findByEmail(username)
                    ?.mapToUserDetails()
                    ?: throw UsernameNotFoundException("Not found!")

    private fun ApplicationUser.mapToUserDetails(): UserDetails =
            User.builder()
                    .username(this.email)
                    .password(this.password)
                    .roles(this.role.name)
                    .build()
}