package com.lww.design.graduation.utils;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtil {
    private volatile RedisTemplate<String,String> redisTemplate;
    public RedisUtil() {
        super();
    }
    public RedisUtil(RedisTemplate<String,String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    
    public RedisTemplate<String,String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String,String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
