package com.gfg.lec5rest.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.lec5rest.entity.Rating;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Objects;

public class RatingSerializer implements Serializer<Rating> {
    ObjectMapper objectMapper = new ObjectMapper();
    @SneakyThrows
    @Override
    public byte[] serialize(final String s, final Rating rating) {
        if(Objects.isNull(rating)){
            return new byte[0];
        }
        return objectMapper.writeValueAsBytes(rating);
    }
}
