package com.nft.nft_app.comment.controller;

import com.nft.nft_app.comment.dto.CommentCreateRequest;
import com.nft.nft_app.comment.dto.CommentResponse;
import com.nft.nft_app.comment.service.CommentService;
import com.nft.nft_app.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> addComment(@RequestBody CommentCreateRequest request) {
        return ResponseEntity.ok(commentService.addComment(request));
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long topicId) {
        return ResponseEntity.ok(commentService.getCommentsByTopic(topicId));
    }
}