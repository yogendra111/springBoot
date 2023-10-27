package com.ksolves.kafka;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("kafka.properties") // TODO: Change file name
@Getter
public class KafkaProducerConfigVars {

    @Value("${kafka.producer.retries}")
    private Integer retries;

    @Value("${kafka.producer.retry-backoff-ms}")
    private Integer retryBackoffTime;

    @Value("${kafka.producer.acks}")
    private String acks;
}
