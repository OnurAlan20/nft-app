package com.nft.nft_app.Like.entity;

import com.nft.nft_app.comment.entity.Comment;
import com.nft.nft_app.common.entity.BaseEntity;
import com.nft.nft_app.topic.entity.Topic;
import com.nft.nft_app.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"like\"")
@Data
public class Like extends BaseEntity{


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;
}

