package org.example.exposed.domain.comment.service

import org.example.exposed.domain.comment.dto.CommentResponse
import org.example.exposed.domain.comment.dto.CreateCommentRequest
import org.example.exposed.domain.comment.dto.UpdateCommentRequest
import org.example.exposed.domain.comment.model.Comment
import org.example.exposed.domain.comment.repository.CommentRepository
import org.example.exposed.domain.feed.repository.FeedRepository
import org.example.exposed.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CommentService(
    private val userRepository: UserRepository,
    private val commentRepository: CommentRepository,
    private val feedRepository: FeedRepository
) {
    @Transactional
    fun createComment(
        request: CreateCommentRequest,
        feedId: Long,
        userId: Long
    ): CommentResponse {
        val user = userRepository.findById(userId)?:throw IllegalStateException("User with id: $userId not found")
        val feed = feedRepository.findById(feedId)?:throw IllegalStateException("Feed with id: $feedId")
        val comment = Comment.new {
            this.title = request.title
            this.feedId = feed.id
            this.creatorId = user.id
            this.content = request.content
            this.createdAt = LocalDateTime.now()
        }
        return CommentResponse.from(comment)
    }

    @Transactional
    fun updateComment(
        request: UpdateCommentRequest,
        feedId: Long,
        commentId: Long,
        userId: Long,
    ): CommentResponse {
        val user = userRepository.findById(userId)?:throw IllegalStateException("User with id: $userId not found")
        val feedAndComment = commentRepository.findByFeedIdAndCommentId(feedId,commentId) ?: throw IllegalStateException("Feed with id: $feedId")
        if (feedAndComment.creatorId != user.id) {
            throw IllegalStateException("User with id: $userId not found")
        }
        feedAndComment.title = request.title
        feedAndComment.content = request.content
        feedAndComment.modifiedAt = LocalDateTime.now()
        return CommentResponse.from(feedAndComment)
    }
}