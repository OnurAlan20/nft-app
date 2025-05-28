package com.nft.nft_app.user.service;

import com.nft.nft_app.user.dto.LoginResponse;
import com.nft.nft_app.user.dto.LoginUserDto;
import com.nft.nft_app.user.dto.RegisterUserDto;
import com.nft.nft_app.user.dto.UpdateUserProfileDTO;
import com.nft.nft_app.user.entity.User;
import com.nft.nft_app.user.repository.UserRepository;
import com.nft.nft_app.user.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public User signup(RegisterUserDto input) {
        User user = new User();
        user.setEmail(input.getEmail());
        user.setUsername(input.getUserName());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

    public User getCurentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public User getUserByFetchingAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return userRepository.findByEmail(user.getEmail()).orElse(null);
    }

    public LoginResponse updateUserProfile(UpdateUserProfileDTO updateUserProfileDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        User userVt = userRepository.findByEmail(user.getEmail()).orElse(null);
        userVt.setUsername(updateUserProfileDTO.getUserName());
        userVt.setPassword(passwordEncoder.encode(updateUserProfileDTO.getPassword()));
        userVt.setEmail(updateUserProfileDTO.getEmail());
        User userNew = userRepository.save(userVt);

        User authenticatedUser = authenticate(new LoginUserDto(updateUserProfileDTO.getEmail(),updateUserProfileDTO.getPassword()));
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setEmail(authenticatedUser.getEmail());
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setFirstName(authenticatedUser.getFirstName());
        loginResponse.setLastName(authenticatedUser.getLastName());
        return loginResponse;
    }
}