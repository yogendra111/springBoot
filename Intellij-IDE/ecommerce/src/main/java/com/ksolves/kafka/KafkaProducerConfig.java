package com.ksolves.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Autowired
    private KafkaProducerConfigVars producerConfigVars;

    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Bean
    public <E> ProducerFactory<String, E> producerFactory() {

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        props.put(ProducerConfig.ACKS_CONFIG,
                producerConfigVars.getAcks());

        props.put(ProducerConfig.RETRIES_CONFIG, producerConfigVars.getRetries());
        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, producerConfigVars.getRetryBackoffTime());

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public <E> ProducerFactory<String, E> dltProducerFactory() {

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        props.put(ProducerConfig.ACKS_CONFIG,
                producerConfigVars.getAcks());

        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

        props.put(ProducerConfig.RETRIES_CONFIG, producerConfigVars.getRetries());
        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, producerConfigVars.getRetryBackoffTime());

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean(name = "dltKafkaTemplate")
    public <E> KafkaTemplate<String, E> dltKafkaTemplate() {
        return new KafkaTemplate<>(dltProducerFactory());
    }

    @Bean
    public <E> KafkaTemplate<String, E> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
