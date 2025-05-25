package com.nft.nft_app.topic.entity;

import com.nft.nft_app.Like.entity.Like;
import com.nft.nft_app.comment.entity.Comment;
import com.nft.nft_app.common.entity.BaseEntity;
import com.nft.nft_app.nft.entity.Nft;
import com.nft.nft_app.nft.enums.NftDurum;
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
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();
}
