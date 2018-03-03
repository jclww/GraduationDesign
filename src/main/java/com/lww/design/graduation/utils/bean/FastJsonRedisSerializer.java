package com.lww.design.graduation.utils.bean;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

@Slf4j
public class FastJsonRedisSerializer implements RedisSerializer<Object> {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Charset charset;

    public FastJsonRedisSerializer() {
        this(DEFAULT_CHARSET);
    }
    FastJsonRedisSerializer(Charset charset) {
        this.charset = charset;
    }

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        log.info("session insert {}", JSON.toJSONString(t));
        return JSON.toJSONString(t).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return JSON.parseObject(str);
    }
}