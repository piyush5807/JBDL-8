package com.gfg.lec5rest.config;
import com.gfg.lec5rest.entity.Rating;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.Properties;

public class KafkaConsumerService {

    @Bean
    Properties kafkaProperties(){
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,RatingDeserializer.class);
        return properties;
    }

    @Bean
    public ConsumerFactory<String, Rating> consumerFactory(){
        return new DefaultKafkaConsumerFactory(kafkaProperties());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Rating> concurrentKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory();
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
        return kafkaListenerContainerFactory;
    }
//
//    @Bean
//    public KafkaTemplate<String,String> kafkaTemplate(){
//        return new KafkaTemplate<>(producerFactory());
//    }
}
