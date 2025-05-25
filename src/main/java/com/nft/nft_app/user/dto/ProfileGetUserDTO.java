package com.nft.nft_app.user.dto;

import com.nft.nft_app.user.entity.User;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class ProfileGetUserDTO {
    private String fullName;
    private String katilimTarihi;
    private Long nftCount;
    private Long topicCount;
    private Long commentCount;
    private String userName;
    private String email;

    public ProfileGetUserDTO(User user){
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("d MMMM yyyy", new Locale("tr"));

        String formatted = user.getCreatedDate()
                .format(formatter)
                .toLowerCase(new Locale("tr"));

        this.fullName = user.getFirstName() + " " + user.getLastName();
        this.katilimTarihi = formatted;
        this.nftCount = (long) user.getNftList().size();
        this.topicCount = (long) user.getTopicList().size();
        this.commentCount = (long) user.getTopicList().size();
        this.userName = user.getUsername();
        this.email = user.getEmail();
    }
}
