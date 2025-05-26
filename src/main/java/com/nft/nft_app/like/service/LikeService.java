package com.nft.nft_app.like.service;

import com.nft.nft_app.comment.entity.Comment;
import com.nft.nft_app.comment.repository.CommentRepository;
import com.nft.nft_app.like.dto.LikeCreateRequest;
import com.nft.nft_app.like.entity.Like;
import com.nft.nft_app.like.repository.LikeRepository;
import com.nft.nft_app.topic.entity.Topic;
import com.nft.nft_app.topic.repository.TopicRepository;
import com.nft.nft_app.user.entity.User;
import com.nft.nft_app.user.repository.UserRepository;
import com.nft.nft_app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final TopicRepository topicRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;

    public void like(LikeCreateRequest request) {
        // ❗ Validasyon: sadece biri dolu olmalı
        if ((request.getTopicId() == null && request.getCommentId() == null)
                || (request.getTopicId() != null && request.getCommentId() != null)) {
            throw new IllegalArgumentException("Exactly one of topicId or commentId must be set.");
        }

        User user = userService.getCurentUser(); // aktif kullanıcı

        Like like = new Like();
        like.setUser(user);

        if (request.getTopicId() != null) {
            Topic topic = topicRepository.findById(request.getTopicId())
                    .orElseThrow(() -> new RuntimeException("Topic not found"));
            if (likeRepository.existsByUserAndTopic(user, topic)) {
                throw new RuntimeException("Already liked this topic.");
            }
            like.setTopic(topic);
        }

        if (request.getCommentId() != null) {
            Comment comment = commentRepository.findById(request.getCommentId())
                    .orElseThrow(() -> new RuntimeException("Comment not found"));
            if (likeRepository.existsByUserAndComment(user, comment)) {
                throw new RuntimeException("Already liked this comment.");
            }
            like.setComment(comment);
        }

        likeRepository.save(like);
    }
}