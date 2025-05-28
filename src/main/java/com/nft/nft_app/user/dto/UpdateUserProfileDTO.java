package com.nft.nft_app.user.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UpdateUserProfileDTO {

    private String userName;

    @Email
    private String email;

    private String password;

}
