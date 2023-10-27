package com.ksolves.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private KafkaConsumerConfigVars consumerConfigVars;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

//    @Autowired
//    CustomRecoveryCallback customRecoveryCallback;

    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                ErrorHandlingDeserializer.class);

        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS,
                StringDeserializer.class);

        props.put(JsonDeserializer.TRUSTED_PACKAGES, consumerConfigVars.getTrustedPackage());

        return props;
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(consumerConfigVars.getConcurrency());

        factory.setMessageConverter(new StringJsonMessageConverter());
        kafkaTemplate.setMessageConverter(new StringJsonMessageConverter());

//        factory.setRecoveryCallback(customRecoveryCallback);
//        factory.setRetryTemplate(retryTemplate());

        return factory;
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(consumerConfigVars.getRetries());
        retryTemplate.setRetryPolicy(simpleRetryPolicy);

        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(consumerConfigVars.getRetryBackoffTime());
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        return retryTemplate;
    }
}