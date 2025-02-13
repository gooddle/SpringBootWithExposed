package org.example.exposed.domain.user.dto

data class SignUpResponse(
    val id: Long,
    val username: String,
    val email: String,
)