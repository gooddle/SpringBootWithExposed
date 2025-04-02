package org.example.exposed.domain.comment.model

import org.example.exposed.domain.comment.table.CommentTable
import org.example.exposed.domain.feed.model.Feed
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Comment(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Comment>(CommentTable)
    var feeds by Feed referencedOn CommentTable.feedId
    var feedId by CommentTable.feedId
    var creatorId by CommentTable.creatorId
    var title by CommentTable.title
    var content by CommentTable.content
    var createdAt by CommentTable.createdAt
    var modifiedAt by CommentTable.modifiedAt
}