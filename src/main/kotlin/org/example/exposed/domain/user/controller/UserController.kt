package org.example.exposed.domain.user.controller

import org.example.exposed.domain.user.dto.LoginRequest
import org.example.exposed.domain.user.dto.LoginResponse
import org.example.exposed.domain.user.dto.SignUpRequest
import org.example.exposed.domain.user.dto.SignUpResponse
import org.example.exposed.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signup")
    fun signUp(
        @RequestBody request: SignUpRequest
    ):ResponseEntity<SignUpResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(request))
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest
    ):ResponseEntity<LoginResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.login(request))
    }
}