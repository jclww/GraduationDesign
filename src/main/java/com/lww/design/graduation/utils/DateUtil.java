package com.lww.design.graduation.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author chenzhen
 * @date 2017/7/4
 */
public class DateUtil {

    private static final Long SECONDS_OF_DAY = 86400L;

    private static final BigDecimal B_SECONDS_OF_DAY = new BigDecimal(86400L);

    /**
     * 代表永久时间，毫秒时间戳
     */
    private static final long FOREVER_TIME = 9999999999L * 1000L;

    /**
     * 约定了的无限大时间，用来标记永久绑定，不再释放
     */
    public static final Date FOREVER_DATETIME = new Date(FOREVER_TIME);


    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_ONLY_DATE_FORMAT = "yyyy-MM-dd";

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static final int ONE_DAY_MILLS = (24 * 60 * 60 * 1000);

    /**
     * 1970-01-01 08:00:00	时间戳
     */
    public static final Date SYSTEM_START = new Date(0);

    public static final String SYSTEM_START_STR = "1970-01-01 08:00:00";

    /**
     * 绑定之后，多少时间没有处理
     */
    public static final Integer DAY_30 = 30;


    /**
     * 计算时间相差天数
     *
     * @param from 开始时间 用s计算
     * @param to   结束时间
     * @return 相差天数
     */
    public static BigDecimal betweenDays(Long from, Long to) {
        return new BigDecimal(to - from).divide(B_SECONDS_OF_DAY, 2, BigDecimal.ROUND_CEILING);
    }

    public static BigDecimal betweenDays(Date from, Date to) {
        if (from == null || to == null) {
            return BigDecimal.ZERO;
        }
        return betweenDays(from.getTime() / 1000, to.getTime() / 1000);
    }

    public static Date parse(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return df.parse(dateStr);
        } catch (Exception e) {
            return new Date(0);
        }
    }

    /**
     * 解析日期
     *
     * @param seconds 秒
     */
    public static Date parseFromSecond(Long seconds) {
        if (seconds == null) {
            return SYSTEM_START;
        }
        return new Date(seconds * 1000);
    }

    /**
     * "yyyy-MM-dd HH:mm:ss" -> timestamp (s)
     */
    public static Long parseFromDateStr(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return df.parse(dateStr).getTime() / 1000;
        } catch (Exception e) {
            return 0L;
        }
    }

    public static Date systemBeginTime() {
        return DateUtil.parse("1970-01-01 08:00:00", DEFAULT_DATE_FORMAT);
    }

    public static boolean smallerThanSysTemTime(Date date) {
        return date == null || date.compareTo(SYSTEM_START) < 0;
    }

    public static boolean smallerThanSysTemTime(Long date) {
        return date == null || date < 0L;
    }


    /**
     * 获取当前时间的秒数
     */
    public static Long currentSeconds() {
        return (System.currentTimeMillis() / 1000);
    }

    /**
     * date -> timestamp (s)
     */
    public static Long toSeconds(Date date) {
        if (date == null) {
            return 0L;
        }
        return date.getTime() / 1000;
    }

    public static Date parse(String date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            LOGGER.error("{}", e);
            throw new RuntimeException();
        }
    }

    /**
     * 按照指定格式，返回当前时间字符串
     */
    public static String currentTimeString() {
        return FastDateFormat.getInstance(DEFAULT_DATE_FORMAT).format(new Date());
    }


    /**
     * 解析日期
     */
    public static String formatFromSeconds(Long seconds) {
        if (seconds == null) {
            return DateFormatUtils.format(0L, DateUtil.DEFAULT_DATE_FORMAT);
        } else {
            return DateFormatUtils.format(1000 * seconds, DateUtil.DEFAULT_DATE_FORMAT);
        }
    }

    /**
     * 日期转成标准时间字符串
     */
    public static String toFormatString(Date date) {
        if (date == null) {
            return DateUtil.SYSTEM_START_STR;
        }
        return DateFormatUtils.format(date, DateUtil.DEFAULT_DATE_FORMAT);
    }

    /**
     * 获取指定日期的开始时间
     */
    public static Date getOneDayStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 获取指定日期的结束时间
     */
    public static Date getOneDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }


    public static boolean rangeIn(Long from, Long to, int delta, TimeUnit timeUnit) {
        Preconditions.checkArgument(from != null, "from不能为空");
        Preconditions.checkArgument(to != null, "to 不能为空");
        Preconditions.checkArgument(timeUnit != null, "时间单位不能为空");

        Long result = Math.abs(from - to);

        if (TimeUnit.SECONDS.equals(timeUnit)) {
            return result < delta * 1000;
        }

        if (TimeUnit.MINUTES.equals(timeUnit)) {
            return result < delta * 60 * 1000;
        }
        if (TimeUnit.HOURS.equals(timeUnit)) {
            return result < delta * 60 * 60 * 1000;
        }

        throw new RuntimeException("不支持");
    }

}
