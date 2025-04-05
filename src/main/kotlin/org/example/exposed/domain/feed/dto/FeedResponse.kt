package org.example.exposed.domain.feed.dto

import org.example.exposed.domain.comment.dto.CommentResponse
import org.example.exposed.domain.feed.model.Feed
import org.example.exposed.domain.feed.model.getComments
import org.example.exposed.domain.user.model.User
import java.time.LocalDateTime

data class FeedResponse(
    val id: Long,
    val creatorId: Long,
    val title: String,
    val userName: String,
    val content: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime?,
    val comments: List<CommentResponse>
) {
    companion object {
        fun from(feed: Feed, user: User): FeedResponse {
            return FeedResponse(
                id = feed.id.value,
                creatorId = feed.creatorId.value,
                title = feed.title,
                content = feed.content,
                userName = user.username,
                comments = feed.getComments().map { CommentResponse.from(it, user) },
                createdAt = feed.createdAt,
                modifiedAt = feed.modifiedAt
            )
        }
    }
}
