package org.example.exposed.domain.comment.controller

import org.example.exposed.domain.comment.dto.CommentResponse
import org.example.exposed.domain.comment.dto.CreateCommentRequest
import org.example.exposed.domain.comment.dto.UpdateCommentRequest
import org.example.exposed.domain.comment.service.CommentService
import org.example.exposed.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentController(
    private val commentService: CommentService,
) {

    @PostMapping("{feedId}/comment")
    fun createComment(
        @RequestBody request: CreateCommentRequest,
        @PathVariable("feedId") feedId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(request,feedId,userPrincipal.id))
    }

    @PutMapping("{feedId}/comment/{commentId}")
    fun updateComment(
        @RequestBody request: UpdateCommentRequest,
        @PathVariable("feedId") feedId: Long,
        @PathVariable("commentId") commentId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(request,feedId,commentId,userPrincipal.id))
    }
}