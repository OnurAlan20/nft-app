package com.nft.nft_app.topic.dto;

import com.nft.nft_app.topic.enums.TopicTur;
import lombok.Data;

@Data
public class TopicCreateRequest {
    private String title;
    private String subTitle;
    private String description;
    private TopicTur topicTur;
}