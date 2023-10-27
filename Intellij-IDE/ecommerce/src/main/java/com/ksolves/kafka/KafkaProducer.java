package com.ksolves.kafka;

import com.ksolves.models.ProductModel;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Value("${kafka.current.topic}")
    String topic;

    @Autowired
    private KafkaTemplate<String, ProductModel> kafkaTemplate;

    public void sendMessage(ProductModel message){
//        ProducerRecord<String, ProductModel> record = new ProducerRecord<>(topic, message);
        LOGGER.info(String.format("Message sent -> %s", message));
        CompletableFuture<SendResult<String, ProductModel>> future = kafkaTemplate.send(topic, message);
        future.whenComplete((sr, ex) -> {
            if(ex == null){
                LOGGER.info("Acknowledgement: {} ", sr.getRecordMetadata().partition());
            }else{
                LOGGER.info("Message: {}", ex.getMessage());
            }
        });
    }

}
