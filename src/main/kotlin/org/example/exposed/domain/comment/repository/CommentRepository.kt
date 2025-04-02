package org.example.exposed.domain.comment.repository

import org.example.exposed.domain.comment.model.Comment
import org.example.exposed.domain.comment.table.CommentTable
import org.jetbrains.exposed.sql.and
import org.springframework.stereotype.Repository

@Repository
class CommentRepository {

    fun findByFeedIdAndCommentId(feedId: Long, commentId: Long): Comment? {
        return Comment.find {
            (CommentTable.feedId eq feedId) and (CommentTable.id eq commentId)
        }.firstOrNull()
    }
}