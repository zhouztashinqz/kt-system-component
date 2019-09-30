package com.snowofsunflower.android.date;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * DateTimeUtils
 */
public class DateTimeUtils {

    public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String PATTERN_LOG_DATE = "HH:mm";
    public static final String PATTERN_CHINA_YEAR_MONTH_DATE = "yyyy年MM月";
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(PATTERN_DATETIME);
    public static final SimpleDateFormat DATE_MILLISECOND = new SimpleDateFormat(PATTERN_DATE_TIME);
    public static final SimpleDateFormat HOUR_MINUE_FORMAT = new SimpleDateFormat(PATTERN_LOG_DATE);
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String YEAR_MONTH_DAY = "yyyy年MM月dd日";
    public static final SimpleDateFormat YEAR_MONTH_DAY_FORMAT = new SimpleDateFormat(YEAR_MONTH_DAY);
    private static final String YEAR = "年";
    private static final String MONTH = "月";
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;
    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    private DateTimeUtils() {
        throw new AssertionError();
    }

    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }


    public static String getYearMonthDay(long timeInMillis) {
        return getTime(timeInMillis, YEAR_MONTH_DAY_FORMAT);
    }

    public static String getHourAndMinnute(long timeInMillis) {
        return getTime(timeInMillis, HOUR_MINUE_FORMAT);
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    public static long getNowTime() {
        return System.currentTimeMillis();
    }

    /**
     * 转化为几天前这种形式。
     *
     * @param millis
     * @return
     */
    public static String format(long millis) {
        long delta = System.currentTimeMillis() - millis;
        if (delta < 1L * ONE_MINUTE) {
//            long seconds = toSeconds(delta);
//            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
            return 1 + ONE_MINUTE_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }


    /**
     * 是否处于夜间0点到6点之间
     *
     * @return
     */
    public static boolean isNight() {
        int hour = DateTimeUtils.getHour(new Date());
        if (hour >= 0 && hour <= 6) {
            return true;
        }
        return false;
    }

    /**
     * 获取年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日份
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回小时
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回分钟
     *
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    /**
     * 返回毫秒
     *
     * @param date
     * @return
     */
    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 日期相加
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDateWithDay(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
        return c.getTime();
    }

    /**
     * 日期相加
     *
     * @param date
     * @return
     */
    public static Date addDateWithMilliSecond(Date date, long milliSecond) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + milliSecond);
        return c.getTime();
    }

    /**
     * 日期相减
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int diffDate(Date date1, Date date2) {
        return (int) ((getMillis(date1) - getMillis(date2)) / (24 * 3600 * 1000));
    }

    /**
     * 日期相减
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long diffDateInMillis(Date date1, Date date2) {
        return (long) ((getMillis(date1) - getMillis(date2)));
    }

    /**
     * 格式化日期对象
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTimeStr(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /*
     **
     * 获取中文年月日期
     */
    public static String getYearMonthForChineseDate(Date date) {
        return formatDate(date, PATTERN_CHINA_YEAR_MONTH_DATE);
    }

    /**
     * 格式化日期对象
     *
     * @param date
     * @return yyyy-MM-dd
     */
    public static String getDateStr(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * 获取当前日期时间字符串
     *
     * @return yyyy-MM-dd
     */
    public static String getCurrentDate() {
        return formatDate(new Date(), "yyyy-MM-dd");
    }

    /**
     * 将字符型日期时间转换为Date
     *
     * @param strDate yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date getDateFromLongStr(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将字符型日期转换为Date
     *
     * @param strDate yyyy-MM-dd
     * @return
     */
    public static Date getDateFromStr(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }


    public static boolean compareDateTime(Date date1, Date date2) {
        return !date1.before(date2) && !date2.before(date1);
    }


    /**
     * 获取指定日期的最后时刻
     *
     * @param date
     * @return yyyy-MM-dd 23:59:59
     */
    public static String getEndDay(Date date) {
        return (new StringBuilder(String.valueOf(getDateStr(date)))).append(" 23:59:59").toString();
    }

    /**
     * 格式化日期时间
     *
     * @param date    需要格式化的日期时间
     * @param pattern 格式化形式，参考上面的静态常量
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        String result = "";
        if (date != null) {
            java.text.DateFormat df = new SimpleDateFormat(pattern);
            result = df.format(date);
        }
        return result;
    }

    /**
     * 将日期字符串转换为Date对象
     *
     * @param date    字符串
     * @param pattern 格式
     * @return
     */
    public static Date parseDate(String date, String pattern) {
        try {
            return (new SimpleDateFormat(pattern)).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取指定时间跟当前时间的间隔，返回中文描述
     *
     * @param time
     * @return
     * @throws Exception
     */
    public static String getTimeDescription(Date time) throws Exception {
        long tt = System.currentTimeMillis() - time.getTime(); // 离现在的时间间隔
        long t = tt / (3600 * 1000);
        if (t > 24) {
            String timeS = getDateTimeStr(time);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            tt = df.parse(df.format(new Date())).getTime() - df.parse(timeS.substring(0, 10)).getTime();
            long d = tt / (24 * 3600 * 1000);
            if (d >= 3) {
                return timeS;
            } else if (d == 2) {
                return "前天";
            } else {
                return "昨天";
            }
        } else {
            if (t > 0) {
                return Long.toString(t) + "小时前";
            } else {
                t = tt / (60 * 1000);
                if (t > 0) {
                    return Long.toString(t) + "分钟前";
                } else {
                    return "刚刚";
                }
            }
        }
    }

    /**
     * Get the date time in simple formatted string, such as '2012-09-14
     * 11:12:00'.
     *
     * @param date
     * @return
     */
    public static String getSimpleDateTime(Date date) {
        if (date == null) {
            return "";
        }

        return new SimpleDateFormat(PATTERN_DATETIME).format(date);
    }

    /**
     * Parse the simple date time literal into Date object.
     *
     * @param dateTime
     * @return
     */
    public static Date parseSimpleDateTime(String dateTime) {
        if (dateTime == null || dateTime.trim().equals("")) {
            return null;
        }

        try {
            return new SimpleDateFormat(PATTERN_DATETIME).parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseChineseDateTime(String dateTime) {
        if (dateTime == null || dateTime.trim().equals("")) {
            return null;
        }

        try {
            return new SimpleDateFormat(PATTERN_CHINA_YEAR_MONTH_DATE).parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Get the date several days ago, e.g. a month ago (30 days).
     *
     * @param days
     * @return
     */
    public static Date getDateTimeDaysAgo(int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.add(Calendar.DAY_OF_MONTH, 0 - days);

        return cal.getTime();
    }

    /***
     * 以yyyy-MM-dd HH:mm样式 格式化日期
     * @param date 日期
     * @return
     */
    public static String getFormatDate(Date date) {
        if (date == null) {
            return null;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.US);
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return null;
    }

    public static String getFormatDate(long time) {

        try {
            //判断位数 PHP位数与Java不同，后面需要补0
            if ((time + "").length() == 10) {
                time = time * 1000;
            }
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
            return format.format(new Date(time));
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return null;
    }

    /**
     * 将yyyy-MM-dd HH:mm样式转换为日期类
     *
     * @param date
     * @return
     */
    public static Date parseDateTime(String date) {
        if (date == null) return new Date();
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String getCurrentDateTime() {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.US);
            return format.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return null;
    }

    /**
     * 获取年月
     *
     * @param date
     * @return YYYY年MM月
     */
    public static String getYearMonth(Date date) {
        String year = String.valueOf(getYear(date));
        String month = String.valueOf(getMonth(date));
        String dateStr = year + YEAR + month + MONTH;
        return dateStr;
    }

}
