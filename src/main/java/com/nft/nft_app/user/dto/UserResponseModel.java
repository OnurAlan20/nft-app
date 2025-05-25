package com.nft.nft_app.user.dto;

import com.nft.nft_app.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseModel {
    
    private String username;

    private String password;

    public UserResponseModel (User user) {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
    }
}
