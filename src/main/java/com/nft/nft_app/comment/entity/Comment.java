package com.nft.nft_app.comment.entity;

import com.nft.nft_app.Like.entity.Like;
import com.nft.nft_app.common.entity.BaseEntity;
import com.nft.nft_app.topic.entity.Topic;
import com.nft.nft_app.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Comment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likeList = new ArrayList<>();

    private String comment;
}
