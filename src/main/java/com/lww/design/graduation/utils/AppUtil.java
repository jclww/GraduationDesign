package com.lww.design.graduation.utils;

import org.apache.commons.lang3.BooleanUtils;

public class AppUtil {
    private static Long DEFAULT_LONG = 0L;

    private static Integer DEFAULT_INTEGER = 0;

    /**
     * 不为null或者默认值
     */
    public static boolean notNullOrDefault(Long value, Long defaultValue) {
        return value != null && !value.equals(defaultValue);
    }


    public static boolean notNullOrDefault(Integer value, Integer defaultValue) {
        return value != null && !value.equals(defaultValue);
    }

    /**
     * 不为null或者默认值
     */
    public static boolean notNullOrDefault(Long value) {
        return notNullOrDefault(value, DEFAULT_LONG);
    }
    /**
     * 不为null或者默认值
     */
    public static boolean notNullOrDefault(Integer value) {
        return notNullOrDefault(value, DEFAULT_INTEGER);
    }


    public static boolean nullOrDefault(Long value) {
        return value == null || DEFAULT_LONG.equals(value);
    }

    public static boolean nullOrDefault(Integer value) {
        return value == null || DEFAULT_INTEGER.equals(value);
    }




    /**
     * 转换为Boolean类型
     * 'true', 'on', 'y', 't', 'yes' or '1' (case insensitive) will return true. Otherwise, false is returned.
     */
    public static Boolean toBoolean(final Object val){
        if (val == null){
            return false;
        }
        return BooleanUtils.toBoolean(val.toString()) || "1".equals(val.toString());
    }

    public static String toString(final Object obj, final String defaultVal) {
        return obj == null ? defaultVal : obj.toString();
    }
}
