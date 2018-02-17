package com.lww.design.graduation.utils;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtil {
    private RedisTemplate<String,String> redisTemplate;

    public RedisUtil() {
    }
    public RedisUtil(RedisTemplate<String,String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setString(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }
    public String getString(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    
    public RedisTemplate<String,String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String,String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
