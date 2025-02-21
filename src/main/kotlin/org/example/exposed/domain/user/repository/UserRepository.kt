package org.example.exposed.domain.user.repository

import org.example.exposed.domain.user.model.User
import org.example.exposed.domain.user.table.UserTable
import org.springframework.stereotype.Repository

@Repository
class UserRepository {

    fun findByEmail(email: String): User? {
        return User.find { UserTable.email eq email }.singleOrNull()

    }

     fun findAll(): List<User> {
        return User.all().toList()

    }

    fun findById(id: Long): User? {
        return User.findById(id)
    }
}




