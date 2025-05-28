package com.nft.nft_app.nft.repository;

import com.nft.nft_app.nft.entity.Nft;
import com.nft.nft_app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftRepository extends JpaRepository<Nft, Long> {
}
