package org.example.exposed.domain.feed.repository

import org.example.exposed.domain.feed.model.Feed
import org.springframework.stereotype.Repository

@Repository
class FeedRepository {

    fun findAll(): List<Feed> {
        return Feed.all().toList()
    }

    fun findById(id: Long): Feed? {
        return Feed.findById(id)
    }
}