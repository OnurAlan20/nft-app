package com.nft.nft_app.user.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String email;
    private long expiresIn;
    private String firstName;
    private String lastName;
}