package com.gfg.lec5rest.config;
import com.gfg.lec5rest.entity.Rating;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Properties;

public class KafkaProducerService {

    @Bean
    Properties kafkaProperties(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,RatingSerializer.class);
        return properties;
    }

    @Bean
    public ProducerFactory<String, Rating> producerFactory(){
        return new DefaultKafkaProducerFactory(kafkaProperties());
    }

    @Bean
    public KafkaTemplate<String,Rating> kafkaTemplate(){
        return new KafkaTemplate<String,Rating>(producerFactory());
    }
}
