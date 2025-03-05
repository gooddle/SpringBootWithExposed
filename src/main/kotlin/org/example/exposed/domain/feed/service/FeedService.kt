package org.example.exposed.domain.feed.service

import org.example.exposed.domain.feed.dto.CreateFeedRequest
import org.example.exposed.domain.feed.dto.FeedResponse
import org.example.exposed.domain.feed.dto.UpdateFeedRequest
import org.example.exposed.domain.feed.model.Feed
import org.example.exposed.domain.feed.repository.FeedRepository
import org.example.exposed.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class FeedService(
    private val userRepository: UserRepository,
    private val feedRepository: FeedRepository,
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

    @Transactional
    fun updateFeed(
        feedId: Long,
        request: UpdateFeedRequest,
    ): FeedResponse {
        val feedEntity = feedRepository.findById(feedId) ?: throw IllegalStateException("Feed not found")
         feedEntity.run {
            this.title = request.title
            this.content = request.content
        }
        return FeedResponse.from(feedEntity)
    }

    @Transactional
    fun getFeedById(
        feedId: Long,
    ): FeedResponse {
        val feedEntity = feedRepository.findById(feedId) ?: throw IllegalStateException("Feed not found")
        return FeedResponse.from(feedEntity)
    }
}