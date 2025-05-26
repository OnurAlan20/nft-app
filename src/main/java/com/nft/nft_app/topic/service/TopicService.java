package com.nft.nft_app.topic.service;

import com.nft.nft_app.comment.repository.CommentRepository;
import com.nft.nft_app.like.repository.LikeRepository;
import com.nft.nft_app.topic.entity.Topic;
import com.nft.nft_app.topic.enums.TopicTur;
import com.nft.nft_app.topic.repository.TopicRepository;
import com.nft.nft_app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import com.nft.nft_app.topic.dto.TopicCreateRequest;
import com.nft.nft_app.topic.dto.TopicResponse;

import com.nft.nft_app.user.entity.User;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserService userService;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    public TopicResponse createTopic(TopicCreateRequest request) {
        User user = userService.getCurentUser();

        Topic topic = new Topic();
        topic.setTitle(request.getTitle());
        topic.setSubTitle(request.getSubTitle());
        topic.setDescription(request.getDescription());
        topic.setTopicTur(request.getTopicTur());
        topic.setUser(user);

        Topic saved = topicRepository.save(topic);

        return new TopicResponse(saved, 0, 0);
    }

    public List<TopicResponse> getAllTopics() {
        return topicRepository.findAll().stream()
                .map(topic -> {
                    int likeCount = (int) likeRepository.countByTopic(topic);
                    int commentCount = commentRepository.findByTopicId(topic.getId()).size();
                    return new TopicResponse(topic, likeCount, commentCount);
                })
                .collect(Collectors.toList());
    }

    public List<TopicResponse> getTopicsByType(TopicTur topicTur) {
        return topicRepository.findByTopicTur(topicTur).stream()
                .map(topic -> {
                    int likeCount = (int) likeRepository.countByTopic(topic);
                    int commentCount = commentRepository.findByTopicId(topic.getId()).size();
                    return new TopicResponse(topic, likeCount, commentCount);
                })
                .collect(Collectors.toList());
    }

    public TopicResponse getById(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found with id: " + id));

        int likeCount = (int) likeRepository.countByTopic(topic);
        int commentCount = commentRepository.findByTopicId(topic.getId()).size();

        return new TopicResponse(topic, likeCount, commentCount);
    }
}
