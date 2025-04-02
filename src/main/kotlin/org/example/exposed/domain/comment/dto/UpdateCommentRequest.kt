package org.example.exposed.domain.comment.dto

data class UpdateCommentRequest(
    val title: String,
    val content: String,
)