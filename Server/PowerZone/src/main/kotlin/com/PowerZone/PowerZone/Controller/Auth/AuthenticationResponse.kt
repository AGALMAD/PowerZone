package com.PowerZone.PowerZone.Controller.Auth

data class AuthenticationResponse(
        val accessToken: String,
        val refreshToken: String,
)