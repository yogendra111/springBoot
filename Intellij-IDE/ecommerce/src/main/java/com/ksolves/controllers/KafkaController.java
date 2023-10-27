package com.ksolves.controllers;

import com.ksolves.kafka.KafkaProducer;
import com.ksolves.models.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    KafkaProducer kafkaProducer;

    @PostMapping("/publish")
    public ResponseEntity<String> publishData(@RequestBody ProductModel productModel){
        kafkaProducer.sendMessage(productModel);
        return ResponseEntity.ok().body("Message Published");
    }

}
