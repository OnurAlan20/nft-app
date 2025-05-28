package com.nft.nft_app.user.controller;

import com.nft.nft_app.user.dto.LoginResponse;
import com.nft.nft_app.user.dto.ProfileGetUserDTO;
import com.nft.nft_app.user.dto.UpdateUserProfileDTO;
import com.nft.nft_app.user.entity.User;
import com.nft.nft_app.user.security.JwtService;
import com.nft.nft_app.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public ProfileGetUserDTO getUserProfile() {
        User currentUser = userService.getUserByFetchingAll();
        return new ProfileGetUserDTO(currentUser);
    }

    @PostMapping("/update")
    public LoginResponse updateUserProfile(@RequestBody UpdateUserProfileDTO updateUserProfileDTO) {
        return userService.updateUserProfile(updateUserProfileDTO);
    }
}
