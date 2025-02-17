package org.example.exposed.domain.user.service


import org.example.exposed.domain.user.dto.LoginRequest
import org.example.exposed.domain.user.dto.LoginResponse
import java.time.LocalDateTime
import org.example.exposed.domain.user.dto.SignUpRequest
import org.example.exposed.domain.user.dto.SignUpResponse
import org.example.exposed.domain.user.model.Role
import org.example.exposed.domain.user.model.User
import org.example.exposed.domain.user.repository.UserRepository
import org.example.exposed.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
    ) {

    @Transactional
    fun signUp(request: SignUpRequest): SignUpResponse {

        if (userRepository.findByEmail(request.email) != null) {
            throw IllegalStateException("User already exists")
        }
        val user = User.new {
            username = request.username
            email = request.email
            password = passwordEncoder.encode(request.password)
            role = Role.USER.name
            createdAt = LocalDateTime.now()
        }
    return SignUpResponse.from(user)
    }

    @Transactional
    fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email)?: throw IllegalStateException("User not found")

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw IllegalStateException("User password mismatch")
        }
        return LoginResponse(
            token = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = request.email,
                role = user.role,
            )
        )

    }
}