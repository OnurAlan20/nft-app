package com.nft.nft_app.topic.repository;

import com.nft.nft_app.topic.entity.Topic;
import com.nft.nft_app.topic.enums.TopicTur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByTopicTur(TopicTur topicTur);
}