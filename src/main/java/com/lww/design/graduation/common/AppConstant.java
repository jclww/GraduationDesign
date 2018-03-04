package com.lww.design.graduation.common;

import java.util.Date;

public class AppConstant {
    /**
     * key前缀
     */
    public static final String KEY_PREFIX = "graduation_design_";
    /**
     * redis的前缀
     */
    public static final String KV_KEY_PREFIX = "lww:graduation_design:";
    /**
     * 系统开始时间 1970-01-01 08:00:00
     */
    public static final Date SYSTEM_BEGIN_TIME = new Date(0L);

    /**
     * shiro session 前缀
     */
    public static final String SHIRO_SESSION_KEY_PREFIX = "shiro-session:";

    /**
     * shiro 过期时间
     */
    public static final Integer SHIRO_SESSION_KEY_TIMEOUT = 1800;

    /**
     * 密码加密后缀
     */
    public static final String PWD_ENCRY_SUFFIX = "A(Q_Q)=:)==&";

    /**
     * 文件上传路径
     */
    public static final String FILE_UPLOAD_PATH = "/data/uploadFile/";

    public static final String AVATAR_UPLOAD_PATH = "/data/uploadFile/image/avatar/";

    /**
     * 帐号key
     */
    public static final String USER_ACCOUNT_KEY = "user:account_id";

    public static final String PNG_SUFFIX = ".png";
    public static final String JPG_SUFFIX = ".jpg";



}
