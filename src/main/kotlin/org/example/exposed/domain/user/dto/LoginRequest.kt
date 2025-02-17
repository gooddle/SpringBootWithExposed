package org.example.exposed.domain.user.dto

data class LoginRequest(
    val email: String,
    val password: String
)
