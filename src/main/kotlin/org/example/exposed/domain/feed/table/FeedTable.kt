package org.example.exposed.domain.feed.table

import org.example.exposed.domain.user.table.UserTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object FeedTable : LongIdTable("feed") {
    val creatorId = reference("creator_id", UserTable)
    val title = varchar("title", 255)
    val content = text("content")
    val createdAt = datetime("created_at")
}