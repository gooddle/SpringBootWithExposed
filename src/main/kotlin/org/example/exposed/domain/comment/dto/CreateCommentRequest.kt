package org.example.exposed.domain.comment.dto

data class CreateCommentRequest(
    val title: String,
    val content: String,
)
