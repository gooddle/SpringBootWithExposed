package org.example.exposed.domain.feed.repository

import org.example.exposed.domain.comment.dto.CommentResponse
import org.example.exposed.domain.comment.table.CommentTable
import org.example.exposed.domain.feed.dto.FeedResponse
import org.example.exposed.domain.feed.model.Feed
import org.example.exposed.domain.feed.table.FeedTable
import org.example.exposed.domain.user.table.UserTable
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.alias
import org.springframework.stereotype.Repository

@Repository
class FeedRepository {

    fun findAll(): List<Feed> {
        return Feed.all().toList()
    }

    fun findById(id: Long): Feed? {
        return Feed.findById(id)
    }

    fun findByIdAndUpdate(id: Long, update: Feed.() -> Unit): Feed? {
        return Feed.findByIdAndUpdate(id, update)
    }

    fun findByIdWithComment(id: Long): FeedResponse? {
        val commentUserAlias = UserTable.alias("comment_user")
        val feedUserAlias = UserTable.alias("feed_user")

        val rows = FeedTable
            .join(feedUserAlias, JoinType.LEFT, FeedTable.creatorId, feedUserAlias[UserTable.id])
            .join(CommentTable, JoinType.LEFT, FeedTable.id, CommentTable.feedId)
            .join(commentUserAlias, JoinType.LEFT, CommentTable.creatorId, commentUserAlias[UserTable.id])
            .select(
                FeedTable.id,
                FeedTable.creatorId,
                FeedTable.title,
                FeedTable.content,
                FeedTable.createdAt,
                FeedTable.modifiedAt,
                feedUserAlias[UserTable.username],
                CommentTable.id,
                CommentTable.creatorId,
                CommentTable.feedId,
                CommentTable.content,
                CommentTable.createdAt,
                CommentTable.modifiedAt,
                CommentTable.title,
                commentUserAlias[UserTable.username]
            )
            .where { FeedTable.id eq id }
            .toList()

        if (rows.isEmpty()) return null

        // feed, 작성자 정보는 첫 row에서만 가져도 충분
        val firstRow = rows.first()

        val feedId = firstRow[FeedTable.id].value
        val creatorId = firstRow[FeedTable.creatorId].value
        val title = firstRow[FeedTable.title]
        val content = firstRow[FeedTable.content]
        val createdAt = firstRow[FeedTable.createdAt]
        val modifiedAt = firstRow[FeedTable.modifiedAt]
        val userName = firstRow[feedUserAlias[UserTable.username]]

        val comments = rows.mapNotNull { row ->
            val commentIdEntity = row[CommentTable.id]
            if (commentIdEntity == null) return@mapNotNull null // 댓글이 없으면 제외

            val commentId = commentIdEntity.value
            val commentContent = row[CommentTable.content]
            val commentCreatedAt = row[CommentTable.createdAt]
            val commentCreatorId = row[CommentTable.creatorId].value
            val commentUserName = row[commentUserAlias[UserTable.username]]
            val commentFeedId = row[CommentTable.feedId].value
            val commentModifiedAt = row[CommentTable.modifiedAt]
            val commentTitle = row[CommentTable.title]

            CommentResponse(
                id = commentId,
                creatorId = commentCreatorId,
                content = commentContent?: "",
                createdAt = commentCreatedAt,
                userName = commentUserName,
                feedId = commentFeedId,
                title = commentTitle,
                modifiedAt = commentModifiedAt,
            )
        }
        return FeedResponse(
            id = feedId,
            creatorId = creatorId,
            title = title,
            content = content,
            userName = userName,
            createdAt = createdAt,
            modifiedAt = modifiedAt,
            comments = comments
        )
    }
}