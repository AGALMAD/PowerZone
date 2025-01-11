package com.PowerZone.PowerZone.Controller.Auth


data class AuthenticationRequest(
        val email: String,
        val password: String,
)