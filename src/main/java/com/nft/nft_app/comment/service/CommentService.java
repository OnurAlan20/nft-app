package com.nft.nft_app.comment.service;

import com.nft.nft_app.comment.dto.CommentCreateRequest;
import com.nft.nft_app.comment.dto.CommentResponse;
import com.nft.nft_app.comment.entity.Comment;
import com.nft.nft_app.comment.repository.CommentRepository;
import com.nft.nft_app.like.repository.LikeRepository;
import com.nft.nft_app.topic.entity.Topic;
import com.nft.nft_app.topic.repository.TopicRepository;
import com.nft.nft_app.user.entity.User;
import com.nft.nft_app.user.repository.UserRepository;
import com.nft.nft_app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TopicRepository topicRepository;
    private final UserService userService;
    private final LikeRepository likeRepository;

    public CommentResponse addComment(CommentCreateRequest request) {
        Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        User user = userService.getCurentUser();

        Comment comment = new Comment();
        comment.setComment(request.getComment());
        comment.setTopic(topic);
        comment.setUser(user);

        return new CommentResponse(commentRepository.save(comment), 0);
    }

    public List<CommentResponse> getCommentsByTopic(Long topicId) {
        return commentRepository.findByTopicId(topicId)
                .stream()
                .map(comment -> new CommentResponse(comment, comment.getLikeCount()))
                .collect(Collectors.toList());
    }
}