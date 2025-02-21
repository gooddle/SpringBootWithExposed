package org.example.exposed.domain.feed.controller

import org.example.exposed.domain.feed.dto.CreateFeedRequest
import org.example.exposed.domain.feed.dto.FeedResponse
import org.example.exposed.domain.feed.service.FeedService
import org.example.exposed.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FeedController(
    private val feedService: FeedService,
) {
    @PostMapping("feed")
    fun createFeed(
        @RequestBody request: CreateFeedRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<FeedResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(feedService.createFeed(request, userPrincipal.id))
    }
}