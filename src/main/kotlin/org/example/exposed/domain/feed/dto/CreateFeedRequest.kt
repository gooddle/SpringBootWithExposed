package org.example.exposed.domain.feed.dto

data class CreateFeedRequest(
    val title: String,
    val content: String,
)
