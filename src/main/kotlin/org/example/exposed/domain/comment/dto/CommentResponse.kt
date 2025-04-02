package org.example.exposed.domain.comment.dto

import org.example.exposed.domain.comment.model.Comment
import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val feedId: Long,
    val creatorId: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime?,
){
    companion object {
        fun from(comment: Comment): CommentResponse {
            return CommentResponse(
                id = comment.id.value,
                feedId = comment.feedId.value,
                creatorId = comment.creatorId.value,
                title = comment.title,
                content = comment.content,
                createdAt = comment.createdAt,
                modifiedAt = comment.modifiedAt
            )
        }
    }
}
