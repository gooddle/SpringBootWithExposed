package org.example.exposed.domain.feed.dto

import org.example.exposed.domain.feed.model.Feed
import java.time.LocalDateTime

data class FeedResponse(
    val id: Long,
    val creatorId: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
){
    companion object {
        fun from(feed: Feed): FeedResponse {
            return FeedResponse(
                id = feed.id.value,
                creatorId = feed.creatorId.value,
                title = feed.title,
                content = feed.content,
                createdAt = feed.createdAt,
            )
        }
    }
}
