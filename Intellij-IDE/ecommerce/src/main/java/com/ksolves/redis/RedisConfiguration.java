package com.ksolves.redis;

import com.ksolves.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/*
* this class is to create beans for redis
 */
@Configuration
@ComponentScan
public class RedisConfiguration {
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("127.0.0.1");
        configuration.setPort(6379);
        return new LettuceConnectionFactory();
    }

    @Bean
    @Scope("prototype")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new GenericToStringSerializer<Long>(Long.class));
//        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(connectionFactory);
//        template.afterPropertiesSet();
        return template;
    }

//    @Bean
//    @Scope("prototype")
//    public RedisMappingOperations redisMappingOperations(RedisTemplate<String, Object> redisTemplate){
//        return new RedisMappingOperations(redisTemplate);
//    }
}
