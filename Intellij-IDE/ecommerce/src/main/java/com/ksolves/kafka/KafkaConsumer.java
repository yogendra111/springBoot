package com.ksolves.kafka;

import com.ksolves.models.ProductModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
//    @Value("${kafka.current.topic}")
//    final String topic = "";

    @KafkaListener(topics = "kafkaApplication", groupId = "group_id")
    public void consume(ProductModel message){
        LOGGER.info(String.format("Message received -> %s", message));
    }

}
