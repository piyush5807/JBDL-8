package com.gfg.lec5rest.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.lec5rest.entity.Rating;
import com.gfg.lec5rest.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    @Autowired
    private RedisRepository ratingRepository;
    ObjectMapper objectMapper = new ObjectMapper();
    @KafkaListener(topics = {"rating"}, groupId = "ratingService")
    public void insertRatingToRedis(String input) throws JsonProcessingException {
        Rating rating = objectMapper.readValue(input,Rating.class);
        ratingRepository.save(rating);
    }
}
