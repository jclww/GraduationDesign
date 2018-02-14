package com.lww.design.graduation.utils.bean;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

public class FastJsonRedisSerializer implements RedisSerializer<Object> {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Object clazz;

    public FastJsonRedisSerializer() {
        this(new String());
    }
    FastJsonRedisSerializer(Object clazz) {
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return JSON.parseObject(str, clazz.getClass());
    }
}