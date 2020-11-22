package com.gfg.lec5rest.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.lec5rest.entity.Rating;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;

public class RatingDeserializer  implements Deserializer<Rating> {
    ObjectMapper objectMapper =new ObjectMapper();
    @SneakyThrows
    @Override
    public Rating deserialize(final String s, final byte[] bytes) {
        return objectMapper.readValue(bytes,Rating.class);
    }
}
