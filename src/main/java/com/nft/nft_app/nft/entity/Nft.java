package com.nft.nft_app.nft.entity;


import com.nft.nft_app.common.entity.BaseEntity;
import com.nft.nft_app.nft.enums.NftBlockChain;
import com.nft.nft_app.nft.enums.NftDurum;
import com.nft.nft_app.nft.enums.NftKatogori;
import com.nft.nft_app.nft.enums.NftTur;
import com.nft.nft_app.user.entity.User;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
public class Nft extends BaseEntity {

    private String name;
    private String artist;
    private String prize;
    private String description;

    @Enumerated(EnumType.STRING)
    private NftDurum nftDurum;

    @Enumerated(EnumType.STRING)
    private NftTur nftTur;

    @Enumerated(EnumType.STRING)
    private NftKatogori nftKatogori;

    @Enumerated(EnumType.STRING)
    private NftBlockChain nftBlockChain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
