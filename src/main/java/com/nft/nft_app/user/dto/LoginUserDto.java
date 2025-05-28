package com.nft.nft_app.user.dto;

import lombok.Data;

@Data
public class LoginUserDto {
    private String email;

    public LoginUserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private String password;
}