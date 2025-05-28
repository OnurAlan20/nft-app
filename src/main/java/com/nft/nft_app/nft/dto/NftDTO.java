package com.nft.nft_app.nft.dto;

import com.nft.nft_app.nft.entity.Nft;
import lombok.Data;

@Data
public class NftDTO {
    private Long id;
    private String name;
    private String artist;
    private String prize;
    private String category;
    private String blockchain;
    private String status;

    public NftDTO(Nft nft) {
        this.id = nft.getId();
        this.name = nft.getName();
        this.artist = nft.getArtist();
        this.prize = nft.getPrize();
        this.category = nft.getNftKatogori().name().toLowerCase();
        this.blockchain = nft.getNftBlockChain().name().toLowerCase();
        this.status = nft.getNftStatus().name().toLowerCase();
    }
}
