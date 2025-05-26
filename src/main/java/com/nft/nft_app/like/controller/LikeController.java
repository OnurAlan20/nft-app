package com.nft.nft_app.like.controller;

import com.nft.nft_app.like.dto.LikeCreateRequest;
import com.nft.nft_app.like.service.LikeService;
import com.nft.nft_app.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Void> like(@RequestBody LikeCreateRequest request) {
        likeService.like(request);
        return ResponseEntity.ok().build();
    }
}
