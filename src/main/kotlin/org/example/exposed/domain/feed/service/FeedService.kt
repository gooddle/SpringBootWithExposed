package org.example.exposed.domain.feed.service

import org.example.exposed.domain.feed.dto.CreateFeedRequest
import org.example.exposed.domain.feed.dto.FeedResponse
import org.example.exposed.domain.feed.model.Feed
import org.example.exposed.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class FeedService(
    private val userRepository: UserRepository
) {


    @Transactional
    fun createFeed(
        request: CreateFeedRequest,
        userId: Long
    ): FeedResponse {
        val user = userRepository.findById(userId) ?: throw IllegalStateException("User not found")
        val feed = Feed.new {
            creatorId = user.id
            title = request.title
            content = request.content
            createdAt = LocalDateTime.now()
        }
    return FeedResponse.from(feed)
    }
}