package com.nft.nft_app.user.dto;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String userName;
}