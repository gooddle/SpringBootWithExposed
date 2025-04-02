package org.example.exposed.domain.comment.table

import org.example.exposed.domain.feed.table.FeedTable
import org.example.exposed.domain.user.table.UserTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object CommentTable: LongIdTable("comment") {
    val feedId = reference("feed", FeedTable)
    val creatorId = reference("creator_id", UserTable)
    val title = varchar("title", 255)
    val content = varchar("content", 255)
    val createdAt = datetime("created")
    val modifiedAt = datetime("modified").nullable()
}