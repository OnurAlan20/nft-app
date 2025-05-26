package com.nft.nft_app.topic.controller;

import com.nft.nft_app.topic.dto.TopicCreateRequest;
import com.nft.nft_app.topic.dto.TopicResponse;
import com.nft.nft_app.topic.entity.Topic;
import com.nft.nft_app.topic.enums.TopicTur;
import com.nft.nft_app.topic.service.TopicService;
import com.nft.nft_app.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicResponse> createTopic(@RequestBody TopicCreateRequest request) {
        return ResponseEntity.ok(topicService.createTopic(request));
    }

    @GetMapping
    public ResponseEntity<List<TopicResponse>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<TopicResponse>> getByType(@PathVariable TopicTur type) {
        return ResponseEntity.ok(topicService.getTopicsByType(type));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.getById(id));
    }
}