package org.example.exposed.domain.feed.dto

import org.example.exposed.domain.feed.model.Feed
import java.time.LocalDateTime

data class ListFeedResponse(
    val id: Long,
    val creatorId: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime?,
) {
    companion object {
        fun from(feed: Feed): ListFeedResponse {
            return ListFeedResponse(
                id = feed.id.value,
                creatorId = feed.creatorId.value,
                title = feed.title,
                content = feed.content,
                createdAt = feed.createdAt,
                modifiedAt = feed.modifiedAt
            )
        }
    }
}