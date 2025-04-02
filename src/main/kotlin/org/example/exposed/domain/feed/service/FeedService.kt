package org.example.exposed.domain.feed.service

import org.example.exposed.domain.feed.dto.CreateFeedRequest
import org.example.exposed.domain.feed.dto.FeedResponse
import org.example.exposed.domain.feed.dto.ListFeedResponse
import org.example.exposed.domain.feed.dto.UpdateFeedRequest
import org.example.exposed.domain.feed.model.Feed
import org.example.exposed.domain.feed.repository.FeedRepository
import org.example.exposed.domain.user.repository.UserRepository
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
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
        userId: Long
    ): FeedResponse {
        val feedEntity = feedRepository.findByIdAndUpdate(feedId){} ?: throw IllegalStateException("Feed not found")
        if (feedEntity.creatorId.value != userId) {
            throw IllegalStateException("권한이 없습니다.")
        }
         feedEntity.title = request.title
         feedEntity.content = request.content
         feedEntity.modifiedAt = LocalDateTime.now()
        return FeedResponse.from(feedEntity)
    }

    @Transactional
    fun getFeedById(
        feedId: Long,
    ): FeedResponse {
        transaction {
            addLogger(StdOutSqlLogger)
        }
        val feedEntity = feedRepository.findByIdWithCommentId(feedId) ?: throw IllegalStateException("Feed not found")
        return FeedResponse.from(feedEntity)
    }

    @Transactional
    fun getFeed(): List<ListFeedResponse> {
        val feeds = feedRepository.findAll()
        return feeds.map { ListFeedResponse.from(it) }
    }
}