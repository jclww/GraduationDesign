package com.lww.design.graduation.utils.bean.shiro;


import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


/**
 * 管理EhCache中Session缓存的Dao
 * 最开始通过继承AbstractSessionDAO实现，发现doReadSession方法调用过于频繁，所以改为通过继承CachingSessionDAO来实现。
 */
@Slf4j
public class CachingShiroSessionDao extends CachingSessionDAO {

    @Setter
    private ShiroSessionRepository sessionRepository;

    /**
     * 重写CachingSessionDAO中readSession方法，如果Session中没有登陆信息就调用doReadSession方法从Redis中重读
     * session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null 代表没有登录，登录后Shiro会放入该值
     */
    @Override
    public Session readSession(final Serializable sessionId) throws UnknownSessionException {
        Session session = getCachedSession(sessionId);
        if (session == null || session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
            session = this.doReadSession(sessionId);
            if (session == null) {
                log.info("There is no session with id [" + sessionId + "]");
                throw new UnknownSessionException("There is no session with id [" + sessionId + "]");
            } else {
                // 缓存
                cache(session, session.getId());
                log.info("local cache sessionId:{} from redis", sessionId);
            }
        }
        return session;
    }

    /**
     * 根据session ID获取session 并redis中重置过期时间
     *
     * @param sessionId 会话ID
     * @return ShiroSession
     */
    @Override
    protected Session doReadSession(final Serializable sessionId) {
        log.debug("begin doReadSession {} ", sessionId);
        Session session = getCachedSession(sessionId);
        if(session != null) {
            log.info("get session from localcache sessionId:{}", session.getId());
            return session;
        }
        try {
            session = sessionRepository.getSession(sessionId);
            if (session != null) {
                // 重置Redis中缓存过期时间
                sessionRepository.refreshSession(sessionId);
                log.info("get session sessionId {} name {} from redis", sessionId, session.getClass().getName());
            }
        } catch (Exception e) {
            log.error("get session from redis error", e);
        }
        return session;
    }

    /**
     * 从Redis中读取，但不重置Redis中缓存过期时间
     */
    public Session doReadSessionWithoutExpire(final Serializable sessionId) {
        Session session = null;
        try {
            session = sessionRepository.getSession(sessionId);
            log.info("get session sessionId {} name {} from redis", sessionId, session.getClass().getName());
        } catch (Exception e) {
            log.error("get session from redis error", e);
        }
        return session;
    }

    /**
     * 如DefaultSessionManager在创建完session后会调用该方法；
     * 如保存到关系数据库/文件系统/NoSQL数据库；即可以实现会话的持久化；
     * 返回session ID；主要此处返回的ID.equals(session.getId())；
     */
    @Override
    protected Serializable doCreate(final Session session) {
        // 创建一个Id并设置给Session
        Serializable sessionId = this.generateSessionId(session);
        assignSessionId(session, sessionId);
        log.info("create session sessionId {} name {} set redis", sessionId, session.getClass().getName());
        try {
            sessionRepository.saveSession(session);
        } catch (Exception e) {
             log.error("save session defeat", e);
        }
        return sessionId;
    }

    /**
     * 更新会话；如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
     */
    @Override
    protected void doUpdate(final Session session) {
        //如果会话过期/停止 没必要再更新了
        try {
            if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
                return;
            }
        } catch (Exception e) {
            log.error("ValidatingSession error{}",e.getMessage());
        }
        try {
            if (session instanceof ShiroSession) {
                // 如果没有主要字段(除lastAccessTime以外其他字段)发生改变
                ShiroSession ss = (ShiroSession) session;
                if (!ss.isChanged()) {
                    return;
                }
                ss.setChanged(false);
                ss.setLastAccessTime(new Date());
                sessionRepository.updateSession(session);
                log.info("update session sessionId {} name {}", session.getId(), session.getClass().getName());
            } else {
                log.error("update session sessionId {} name {} error", session.getId(), session.getClass().getName());
            }
        } catch (Exception e) {
            log.error("error:{}", e.getMessage());
        }
    }


    @Override
    public void update(Session session) throws UnknownSessionException {
        this.doUpdate(session);
    }

    /**
     * 删除会话；当会话过期/会话停止（如用户退出时）会调用
     */
    @Override
    public void doDelete(final Session session) {
        log.info("delete session sessionId:{} ", session.getId());
        try {
            sessionRepository.deleteSession(session.getId());
            this.unCache(session.getId());
            log.info("delete shiro session id {} success", session.getId());
        } catch (Exception e) {
            log.error("delete shiro session id {} error:{}", e.getMessage());
        }
    }

    /**
     * 删除cache中缓存的Session
     */
    public void unCache(final Serializable sessionId) {
        try {
            Session session = super.getCachedSession(sessionId);
            super.uncache(session);
            log.info("local cache Session id {} invalid", sessionId);
        } catch (Exception e) {
            log.error("delete local cache defeat{}", e.getMessage());
        }
    }


    /**
     * 返回本机Ehcache中Session
     */
    public Collection<Session> getEhCacheActiveSessions() {
        return super.getActiveSessions();
    }

}
