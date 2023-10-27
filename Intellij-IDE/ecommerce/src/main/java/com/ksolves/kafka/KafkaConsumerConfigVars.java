package com.ksolves.kafka;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("kafka.properties")
@Getter
public class KafkaConsumerConfigVars {

    @Value("${kafka.listener.concurrency}")
    private Integer concurrency;

    @Value("${kafka.consumer.retries:3}")
    private Integer retries;

    @Value("${kafka.consumer.retry-backoff-ms:500}")
    private long retryBackoffTime;

    @Value("${kafka.json.trusted.packages}")
    private String trustedPackage;
}
