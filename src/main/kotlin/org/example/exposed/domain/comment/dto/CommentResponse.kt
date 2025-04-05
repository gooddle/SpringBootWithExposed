package org.example.exposed.domain.comment.dto

import org.example.exposed.domain.comment.model.Comment
import org.example.exposed.domain.user.model.User
import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val feedId: Long,
    val creatorId: Long,
    val userName: String,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime?,
){
    companion object {
        fun from(comment: Comment, user:User): CommentResponse {
            return CommentResponse(
                id = comment.id.value,
                feedId = comment.feedId.value,
                creatorId = comment.creatorId.value,
                title = comment.title,
                userName = user.username,
                content = comment.content,
                createdAt = comment.createdAt,
                modifiedAt = comment.modifiedAt
            )
        }
    }
}
