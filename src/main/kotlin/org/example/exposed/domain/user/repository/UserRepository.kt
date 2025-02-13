package org.example.exposed.domain.user.repository

import org.example.exposed.domain.user.model.Users
import org.example.exposed.domain.user.table.UserTable
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserRepository {

    fun findByEmail(email: String): Users? {
        return transaction {
            Users.find { UserTable.email eq email }.singleOrNull()
        }
    }

     fun findAll(): List<Users> {
        return transaction {
            Users.all().toList()
        }
    }

     fun save(user: Users): Users {
        return transaction {
            user
        }
    }

     fun deleteById(id: Long) {
        transaction {
            Users.findById(id)?.delete()
        }
    }
}




