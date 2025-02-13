package org.example.exposed.domain.user.service

import org.example.exposed.domain.user.dto.SignUpRequest
import org.example.exposed.domain.user.dto.SignUpResponse
import org.example.exposed.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServic(
    private val userRepository: UserRepository,
) {

    fun signUp(request: SignUpRequest): SignUpResponse {
        TODO()
    }
}