package com.ksolves.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public class RedisMappingOperations {
    private String key;
    private Long hashKey;
    private Object object;
    private ObjectMapper mapper;
//    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Object, Object> ops;

    public void initialization(){
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        redisTemplate = context.getBean("redisTemplate", RedisTemplate.class);
        RedisSerializer<Class<?>> customSerializer = new CustomRedisSerializer(mapper, object.getClass());
        redisTemplate.setHashValueSerializer(customSerializer);
        ops = redisTemplate.opsForHash();
    }

    RedisMappingOperations(String key, Long hashKey, Object object, ObjectMapper mapper){
        this.key = key;
        this.hashKey = hashKey;
        this.object = object;
        this.mapper = mapper;
        initialization();
    }

    public void saveData(){
        ops.put(key, hashKey, object);
    }

    public Object getData(){
        return ops.get(key, hashKey);
    }

}
