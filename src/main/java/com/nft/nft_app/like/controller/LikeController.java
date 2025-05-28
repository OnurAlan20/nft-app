package com.nft.nft_app.like.controller;

import com.nft.nft_app.like.dto.LikeCreateRequest;
import com.nft.nft_app.like.service.LikeService;
import com.nft.nft_app.user.entity.User;
import com.nft.nft_app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> like(@RequestBody LikeCreateRequest request) {
        likeService.like(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> unlike(@RequestBody LikeCreateRequest request) {
        likeService.unlike(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/is-liked")
    public ResponseEntity<Boolean> isLiked(
            @RequestParam(required = false) Long topicId,
            @RequestParam(required = false) Long commentId
    ) {
        if ((topicId == null && commentId == null) || (topicId != null && commentId != null)) {
            throw new IllegalArgumentException("Exactly one of topicId or commentId must be set.");
        }

        User user = userService.getCurentUser();
        boolean isLiked;

        if (topicId != null) {
            isLiked = likeService.isTopicLikedByUser(user, topicId);
        } else {
            isLiked = likeService.isCommentLikedByUser(user, commentId);
        }

        return ResponseEntity.ok(isLiked);
    }

}
