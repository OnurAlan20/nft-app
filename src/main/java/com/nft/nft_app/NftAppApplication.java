package com.nft.nft_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class NftAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NftAppApplication.class, args);
	}

}
