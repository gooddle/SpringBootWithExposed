package org.example.exposed.domain.user.model

import org.example.exposed.domain.user.table.UserTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class User(id: EntityID<Long>):LongEntity(id) {
    companion object : LongEntityClass<User>(UserTable)

    var username by UserTable.username
    var email by UserTable.email
    var password by UserTable.password
    var createdAt by UserTable.createdAt
}
