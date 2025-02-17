package org.example.exposed.domain.user.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object UserTable : LongIdTable("user") {
    val username = varchar("name", 255)
    val email = varchar("email", 255).uniqueIndex()
    val password = varchar("password", 255)
    val role = varchar("roles", 255)
    val createdAt = datetime("createdAt")
}