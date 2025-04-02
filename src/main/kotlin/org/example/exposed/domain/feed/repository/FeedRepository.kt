package org.example.exposed.domain.feed.repository

import org.example.exposed.domain.comment.table.CommentTable
import org.example.exposed.domain.feed.model.Feed
import org.example.exposed.domain.feed.table.FeedTable
import org.jetbrains.exposed.sql.selectAll
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

    fun findByIdWithCommentId(id: Long): Feed? {
       return (FeedTable leftJoin CommentTable)
            .selectAll()
            .where { FeedTable.id eq id }
            .map { Feed.wrapRow(it) }
            .firstOrNull()
    }
}