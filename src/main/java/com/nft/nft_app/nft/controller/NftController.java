package com.nft.nft_app.nft.controller;


import com.nft.nft_app.nft.dto.NftDTO;
import com.nft.nft_app.nft.entity.Nft;
import com.nft.nft_app.user.entity.User;
import com.nft.nft_app.user.repository.UserRepository;
import com.nft.nft_app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nft")
public class NftController {

    private final UserService userService;
    private final UserRepository userRepository;

    public NftController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/getAll")
    public List<NftDTO> getUserNfts() {
        User currentUser = userService.getCurentUser();
        User userVt = userRepository.findByEmailWithNfts(currentUser.getEmail()).orElse(null);
        List<Nft> nftList = userVt.getNftList();

        return nftList.stream()
                .map(NftDTO::new)
                .collect(Collectors.toList());
    }

}
