package org.example.exposed.domain.user.dto

import org.example.exposed.domain.user.model.User

data class SignUpResponse(
    val id: Long,
    val username: String,
    val email: String,
){
    companion object{
        fun from(users: User): SignUpResponse{
            return SignUpResponse(
                id = users.id.value,
                username = users.username,
                email = users.email,
            )
        }
    }
}