package com.nft.nft_app.comment.dto;

import com.nft.nft_app.comment.entity.Comment;
import lombok.Data;

@Data
public class CommentResponse {

    private Long id;
    private String comment;
    private String username;
    private int likeCount;

    public CommentResponse(Comment comment, int likeCount) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.username = comment.getUser().getUsername();
        this.likeCount = likeCount;
    }
}
