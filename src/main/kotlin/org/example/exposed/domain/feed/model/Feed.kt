package org.example.exposed.domain.feed.model

import org.example.exposed.domain.comment.model.Comment
import org.example.exposed.domain.comment.table.CommentTable
import org.example.exposed.domain.feed.table.FeedTable
import org.example.exposed.domain.user.model.User
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Feed(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Feed>(FeedTable)
    var creator by User referencedOn FeedTable.creatorId
    var creatorId  by FeedTable.creatorId
    var title by FeedTable.title
    var content by FeedTable.content
    var createdAt by FeedTable.createdAt
    var modifiedAt by FeedTable.modifiedAt
}

fun Feed.getComments(): List<Comment> {
    println(this.id::class)
    return Comment.find { CommentTable.feedId eq this@getComments.id }.toList()
}


