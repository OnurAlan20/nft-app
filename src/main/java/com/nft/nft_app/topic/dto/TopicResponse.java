package com.nft.nft_app.topic.dto;

import com.nft.nft_app.topic.entity.Topic;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class TopicResponse {
    private Long id;
    private String title;
    private String subTitle;
    private String description;
    private String username;
    private int likeCount;
    private int commentCount;
    private LocalDateTime createdDate;

    // likeCount ve commentCount dışarıdan alınır
    public TopicResponse(Topic topic, int likeCount, int commentCount) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.subTitle = topic.getSubTitle();
        this.description = topic.getDescription();
        this.username = topic.getUser().getUsername();
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.createdDate = topic.getCreatedDate();
    }
}