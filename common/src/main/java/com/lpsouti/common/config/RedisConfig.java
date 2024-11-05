package com.lpsouti.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置redis连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        // TODO 设置序列化redis value的方式

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
