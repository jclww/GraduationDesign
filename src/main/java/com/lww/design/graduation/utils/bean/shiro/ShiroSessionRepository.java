package com.lww.design.graduation.utils.bean.shiro;


import com.lww.design.graduation.common.AppConstant;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;


/**
 * 管理Redis中的Session
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@Slf4j
public class ShiroSessionRepository {

    @Getter
    @Setter
    private RedisTemplate<String, Session> redisTemplate;


    /**
     * 保存session
     */
    public void saveSession(final Session session) {
        try {
            getRedisTemplate().opsForValue()
                    .set(
                            buildRedisSessionKey(session.getId())
                            , session
                            , AppConstant.SHIRO_SESSION_KEY_TIMEOUT
                            , TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("save session to redis error");
        }
    }

    /**
     * 更新session
     */
    public void updateSession(final Session session) {
        try {
            getRedisTemplate().boundValueOps(buildRedisSessionKey(session.getId()))
                    .set(session
                            , AppConstant.SHIRO_SESSION_KEY_TIMEOUT
                            , TimeUnit.SECONDS
            );
        } catch (Exception e) {
            log.error("update session error");
        }
    }


    /**
     * 刷新session
     */
    public void refreshSession(final Serializable sessionId) {
        getRedisTemplate().expire(
                buildRedisSessionKey(sessionId)
                , AppConstant.SHIRO_SESSION_KEY_TIMEOUT
                , TimeUnit.SECONDS
        );
    }


    /**
     * 删除session
     */
    public void deleteSession(final Serializable id) {
        try {
            getRedisTemplate().delete(buildRedisSessionKey(id));
        } catch (Exception e) {
            log.error("delete session error");
        }
    }


    /**
     * 获取session
     */
    public Session getSession(final Serializable id) {
        Session session = null;
        try {
            session = getRedisTemplate().boundValueOps(buildRedisSessionKey(id)).get();
        } catch (Exception e) {
            log.info("get session error");
        }
        return session;
    }

    /**
     * 通过sessionId获取sessionKey
     */
    private String buildRedisSessionKey(final Serializable sessionId) {
        return AppConstant.SHIRO_SESSION_KEY_PREFIX + sessionId;
    }
}
