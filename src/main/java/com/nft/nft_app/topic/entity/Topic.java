package com.nft.nft_app.topic.entity;

import com.nft.nft_app.like.entity.Like;
import com.nft.nft_app.comment.entity.Comment;
import com.nft.nft_app.common.BaseEntity;
import com.nft.nft_app.topic.enums.TopicTur;
import com.nft.nft_app.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Topic extends BaseEntity {
    private String title;
    private String subTitle;
    private String description;

    @Enumerated(EnumType.STRING)
    private TopicTur topicTur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "like_count")
    private int likeCount = 0;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();
}
