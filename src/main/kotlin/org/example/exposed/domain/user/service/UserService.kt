package org.example.exposed.domain.user.service


import java.time.LocalDateTime
import org.example.exposed.domain.user.dto.SignUpRequest
import org.example.exposed.domain.user.dto.SignUpResponse
import org.example.exposed.domain.user.model.User
import org.example.exposed.domain.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    ) {

    @Transactional
    fun signUp(request: SignUpRequest): SignUpResponse {

        if (userRepository.findByEmail(request.email) != null) {
            throw IllegalArgumentException("User already exists")
        }
        val user = User.new {
            username = request.username
            email = request.email
            password = passwordEncoder.encode(request.password)
            createdAt = LocalDateTime.now()
        }
    return SignUpResponse.from(user)
    }
}