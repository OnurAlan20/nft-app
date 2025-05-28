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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {

    private final LikeRepository likeRepository;
    private final TopicRepository topicRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;

    public void like(LikeCreateRequest request) {
        if ((request.getTopicId() == null && request.getCommentId() == null)
                || (request.getTopicId() != null && request.getCommentId() != null)) {
            throw new IllegalArgumentException("Exactly one of topicId or commentId must be set.");
        }

        User user = userService.getCurentUser();
        Like like = new Like();
        like.setUser(user);

        if (request.getTopicId() != null) {
            Topic topic = topicRepository.findById(request.getTopicId())
                    .orElseThrow(() -> new RuntimeException("Topic not found"));

            if (likeRepository.existsByUserAndTopic(user, topic)) {
                throw new RuntimeException("Already liked this topic.");
            }

            topic.setLikeCount(topic.getLikeCount() + 1);
            topicRepository.save(topic);

            like.setTopic(topic);
        }

        if (request.getCommentId() != null) {
            Comment comment = commentRepository.findById(request.getCommentId())
                    .orElseThrow(() -> new RuntimeException("Comment not found"));

            if (likeRepository.existsByUserAndComment(user, comment)) {
                throw new RuntimeException("Already liked this comment.");
            }

            comment.setLikeCount(comment.getLikeCount() + 1);
            commentRepository.save(comment);

            like.setComment(comment);
        }

        likeRepository.save(like);
    }


    public void unlike(LikeCreateRequest request) {
        if ((request.getTopicId() == null && request.getCommentId() == null)
                || (request.getTopicId() != null && request.getCommentId() != null)) {
            throw new IllegalArgumentException("Exactly one of topicId or commentId must be set.");
        }

        User user = userService.getCurentUser();

        if (request.getTopicId() != null) {
            Topic topic = topicRepository.findById(request.getTopicId())
                    .orElseThrow(() -> new RuntimeException("Topic not found"));

            Like like = likeRepository.findByUserAndTopic(user, topic)
                    .orElseThrow(() -> new RuntimeException("Like not found for topic"));

            likeRepository.delete(like);
            topic.setLikeCount(topic.getLikeCount() - 1);
            topicRepository.save(topic);
        }

        if (request.getCommentId() != null) {
            Comment comment = commentRepository.findById(request.getCommentId())
                    .orElseThrow(() -> new RuntimeException("Comment not found"));

            Like like = likeRepository.findByUserAndComment(user, comment)
                    .orElseThrow(() -> new RuntimeException("Like not found for comment"));

            likeRepository.delete(like);
            comment.setLikeCount(comment.getLikeCount() - 1);
            commentRepository.save(comment);
        }
    }

    public boolean isTopicLikedByUser(User user, Long topicId) {
        return likeRepository.existsByUserIdAndTopicId(user.getId(), topicId);
    }

    public boolean isCommentLikedByUser(User user, Long commentId) {
        return likeRepository.existsByUserIdAndCommentId(user.getId(), commentId);
    }

}
