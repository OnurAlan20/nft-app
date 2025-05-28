package com.nft.nft_app.common;

import com.nft.nft_app.comment.entity.Comment;
import com.nft.nft_app.comment.repository.CommentRepository;
import com.nft.nft_app.like.entity.Like;
import com.nft.nft_app.like.repository.LikeRepository;
import com.nft.nft_app.nft.entity.Nft;
import com.nft.nft_app.nft.enums.NftBlockChain;
import com.nft.nft_app.nft.enums.NftKatogori;
import com.nft.nft_app.nft.enums.NftStatus;
import com.nft.nft_app.nft.enums.NftTur;
import com.nft.nft_app.nft.repository.NftRepository;
import com.nft.nft_app.topic.entity.Topic;
import com.nft.nft_app.topic.enums.TopicTur;
import com.nft.nft_app.topic.repository.TopicRepository;
import com.nft.nft_app.user.entity.User;
import com.nft.nft_app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Random;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase(
            UserRepository userRepository,
            NftRepository nftRepository,
            TopicRepository topicRepository,
            CommentRepository commentRepository,
            LikeRepository likeRepository
    ) {
        return args -> {
            // Kullanıcılar
            User user1 = new User();
            user1.setFirstName("Leonardo");
            user1.setLastName("da Vinci");
            user1.setUsername("leonardo");
            user1.setEmail("onur@gmail.com");
            user1.setPassword(passwordEncoder.encode("x1x2x3x4x5x6"));
            user1.setRoles(List.of("USER"));

            User user2 = new User();
            user2.setFirstName("Vincent");
            user2.setLastName("van Gogh");
            user2.setUsername("vangogh");
            user2.setEmail("vincent@nft.com");
            user2.setPassword(passwordEncoder.encode("123456"));
            user2.setRoles(List.of("USER"));

            User user3 = new User();
            user3.setFirstName("Frida");
            user3.setLastName("Kahlo");
            user3.setUsername("frida");
            user3.setEmail("frida@nft.com");
            user3.setPassword(passwordEncoder.encode("123456"));
            user3.setRoles(List.of("USER"));

            User user4 = new User();
            user4.setFirstName("Salvador");
            user4.setLastName("Dali");
            user4.setUsername("dali");
            user4.setEmail("dali@nft.com");
            user4.setPassword(passwordEncoder.encode("123456"));
            user4.setRoles(List.of("USER"));

            User user5 = new User();
            user5.setFirstName("Pablo");
            user5.setLastName("Picasso");
            user5.setUsername("picasso");
            user5.setEmail("picasso@nft.com");
            user5.setPassword(passwordEncoder.encode("123456"));
            user5.setRoles(List.of("USER"));

            userRepository.saveAll(List.of(user1, user2, user3, user4, user5));

            // NFT'ler
            List<Nft> nftList = List.of(
                    createNft("Mona Lisa NFT", "Leonardo da Vinci", "120.5", "Dijital versiyonu ile karşınızda.", NftStatus.LISTED, NftTur.KRIPTO_SANATCILAR, NftKatogori.SANAT, NftBlockChain.ETHEREUM, user1),
                    createNft("The Starry Night NFT", "Vincent van Gogh", "99.99", "Gece yıldızları artık zincirde.", NftStatus.LISTED, NftTur.MODERN_SANAT, NftKatogori.SANAT, NftBlockChain.POLYGON, user2),
                    createNft("Vitruvian Man NFT", "Leonardo da Vinci", "78.45", "İnsan vücudu dijitalde.", NftStatus.NOT_LISTED, NftTur.KRIPTO_SANATCILAR, NftKatogori.OYUN, NftBlockChain.SOLANA, user1),
                    createNft("Self Portrait NFT", "Frida Kahlo", "55.0", "Kahlo'nun en özel eseri.", NftStatus.LISTED, NftTur.MODERN_SANAT, NftKatogori.SANAT, NftBlockChain.ETHEREUM, user3),
                    createNft("Persistence of Memory NFT", "Salvador Dali", "65.75", "Zamanın eridiği an.", NftStatus.NOT_LISTED, NftTur.MODERN_SANAT, NftKatogori.FOTOGRAF, NftBlockChain.POLYGON, user4),
                    createNft("Guernica NFT", "Pablo Picasso", "110.0", "Savaşın dijital çığlığı.", NftStatus.LISTED, NftTur.KRIPTO_SANATCILAR, NftKatogori.SANAT, NftBlockChain.SOLANA, user5)
            );

            nftRepository.saveAll(nftList);

            // Comment içerikleri (25 tane)
            List<String> comments = List.of(
                    "Gerçekten etkileyici bir eser!",
                    "Bu NFT konsepti çok yenilikçi.",
                    "Sanat dijitalleşiyor, harika!",
                    "İlham verici bir koleksiyon.",
                    "Bu eser ruhuma dokundu.",
                    "Dijital sanatın gücü bu işte.",
                    "Blockchain ile sanat birleşmiş.",
                    "Tarihi eserlerin geleceği.",
                    "Modern sanatın zirvesi!",
                    "Bunu koleksiyonuma katmalıyım.",
                    "Muhteşem bir perspektif.",
                    "Renk uyumu çok başarılı.",
                    "Koleksiyonerler için eşsiz.",
                    "Gerçekten özgün bir stil.",
                    "Tek kelimeyle şahane.",
                    "Bu eserin dijital hali efsane.",
                    "Farklı bakış açılarıyla zengin.",
                    "Emeğe saygı!",
                    "Gerçekten çok yaratıcı.",
                    "Bu kadar detay beklemiyordum.",
                    "NFT dünyasına güçlü bir katkı.",
                    "Bunu paylaşmalıyım.",
                    "Zamanın ötesinde bir tasarım.",
                    "Rönesans dijital çağda yeniden doğuyor.",
                    "Sanat severler için hazine."
            );

            Random random = new Random();


            List<String> topics = List.of(
                    "Gerçekten etkileyici bir eser!",
                    "Bu NFT konsepti çok yenilikçi.",
                    "Sanat dijitalleşiyor, harika!",
                    "İlham verici bir koleksiyon.",
                    "Bu eser ruhuma dokundu.",
                    "Dijital sanatın gücü bu işte.",
                    "Blockchain ile sanat birleşmiş.",
                    "Tarihi eserlerin geleceği.",
                    "Modern sanatın zirvesi!",
                    "Bunu koleksiyonuma katmalıyım.",
                    "Muhteşem bir perspektif.",
                    "Renk uyumu çok başarılı.",
                    "Koleksiyonerler için eşsiz.",
                    "Gerçekten özgün bir stil.",
                    "Tek kelimeyle şahane.",
                    "Bu eserin dijital hali efsane.",
                    "Farklı bakış açılarıyla zengin.",
                    "Emeğe saygı!",
                    "Gerçekten çok yaratıcı.",
                    "Bu kadar detay beklemiyordum.",
                    "NFT dünyasına güçlü bir katkı.",
                    "Bunu paylaşmalıyım.",
                    "Zamanın ötesinde bir tasarım.",
                    "Rönesans dijital çağda yeniden doğuyor.",
                    "Sanat severler için hazine."
            );
            for (int i = 1; i <= 5; i++) {
                Topic topic = new Topic();
                topic.setTitle("NFT ve Sanat " + i);
                topic.setSubTitle("NFT Dönemi " + i);
                topic.setDescription("Sanat dünyasında NFT etkisi " + i);
                topic.setTopicTur(TopicTur.TARTISMA);
                topic.setUser(user1);
                topic.setLikeCount(5);
                topicRepository.save(topic);

                for (int j = 0; j < 5; j++) {
                    User commentUser = switch (j % 5) {
                        case 0 -> user1;
                        case 1 -> user2;
                        case 2 -> user3;
                        case 3 -> user4;
                        default -> user5;
                    };
                    Comment comment = new Comment();
                    comment.setComment(comments.get(random.nextInt(comments.size())));
                    comment.setUser(commentUser);
                    comment.setTopic(topic);
                    comment.setLikeCount(3);
                    commentRepository.save(comment);

                    likeRepository.saveAll(List.of(
                            createLike(user1, comment, null),
                            createLike(user2, comment, null),
                            createLike(user3, comment, null)
                    ));
                }

                likeRepository.saveAll(List.of(
                        createLike(user1, null, topic),
                        createLike(user2, null, topic),
                        createLike(user3, null, topic),
                        createLike(user4, null, topic),
                        createLike(user5, null, topic)
                ));
            }

            System.out.println("Gerçekçi veriler başarıyla yüklendi ✅✅✅");
        };
    }

    private Nft createNft(String name, String artist, String prize, String description, NftStatus status, NftTur tur, NftKatogori katogori, NftBlockChain chain, User user) {
        Nft nft = new Nft();
        nft.setName(name);
        nft.setArtist(artist);
        nft.setPrize(prize);
        nft.setDescription(description);
        nft.setNftStatus(status);
        nft.setNftTur(tur);
        nft.setNftKatogori(katogori);
        nft.setNftBlockChain(chain);
        nft.setUser(user);
        return nft;
    }

    private Like createLike(User user, Comment comment, Topic topic) {
        Like like = new Like();
        like.setUser(user);
        like.setComment(comment);
        like.setTopic(topic);
        return like;
    }
}
