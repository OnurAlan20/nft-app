package com.nft.nft_app.comment.dto;


import lombok.Data;

@Data
public class CommentCreateRequest {
    private Long topicId;
    private String comment;
}
