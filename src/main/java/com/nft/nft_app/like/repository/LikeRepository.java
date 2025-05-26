package com.nft.nft_app.like.repository;

import com.nft.nft_app.comment.entity.Comment;
import com.nft.nft_app.like.entity.Like;
import com.nft.nft_app.topic.entity.Topic;
import com.nft.nft_app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    // Beğeni var mı kontrolü için
    boolean existsByUserAndTopic(User user, Topic topic);
    boolean existsByUserAndComment(User user, Comment comment);

    // Sayım için
    long countByTopic(Topic topic);
    long countByComment(Comment comment);

}
