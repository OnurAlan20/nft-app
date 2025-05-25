package com.nft.nft_app.user.controller;

import com.nft.nft_app.user.dto.LoginResponse;
import com.nft.nft_app.user.dto.LoginUserDto;
import com.nft.nft_app.user.dto.RegisterUserDto;
import com.nft.nft_app.user.entity.User;
import com.nft.nft_app.user.security.JwtService;
import com.nft.nft_app.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public User register(@RequestBody RegisterUserDto registerUserDto) {

        return userService.signup(registerUserDto);
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = userService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setEmail(authenticatedUser.getEmail());
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setFirstName(authenticatedUser.getFirstName());
        loginResponse.setLastName(authenticatedUser.getLastName());
        return loginResponse;
    }

    @GetMapping("/me")
    public User authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return currentUser;
    }
}