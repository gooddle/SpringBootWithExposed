package org.example.exposed.domain.user.model

import org.example.exposed.domain.user.table.UserTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID


class Users(id: EntityID<Long>):LongEntity(id) {
    companion object : LongEntityClass<Users>(UserTable)

    var name by UserTable.name
    var email by UserTable.email
    var password by UserTable.password
    var createdAt by UserTable.createdAt
}
