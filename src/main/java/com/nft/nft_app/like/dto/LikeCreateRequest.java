package com.nft.nft_app.like.dto;

import lombok.Data;

@Data
public class LikeCreateRequest {

    // Kullanıcı yalnızca birini göndermelidir:
    // Ya bir gönderiyi (Topic) beğenmek için
    // Ya da bir yorumu (Comment) beğenmek için

    private Long topicId;   // Opsiyonel - Gönderiye like
    private Long commentId; // Opsiyonel - Yoruma like
}