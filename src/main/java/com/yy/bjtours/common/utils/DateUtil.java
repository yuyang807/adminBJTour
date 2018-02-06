package com.yy.bjtours.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtil
 *
 * @author lufl
 * @date 2016/3/22
 */
public class DateUtil extends org.apache.commons.lang3.time.DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    private static final String LINE_PATTERN = "yyyy-MM-dd";
    private static final String NOLINE_PATTERN = "yyyyMMdd";
    private static final String TEMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();
    private static final Object object = new Object();

    private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
        SimpleDateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            synchronized (object) {
                dateFormat = new SimpleDateFormat(pattern);
                dateFormat.setLenient(false);
                threadLocal.set(dateFormat);
            }
        }
        dateFormat.applyPattern(pattern);
        return dateFormat;
    }

    //date to String
    public static String DToSLine(Date date) {
        DateFormat format = getDateFormat(LINE_PATTERN);
        return format.format(date);
    }

    public static String DToSNoline(Date date) {
        DateFormat format = getDateFormat(NOLINE_PATTERN);
        return format.format(date);
    }


    //String to date
    public static Date SLineToD(String dateStr) {
        return SToD(dateStr, LINE_PATTERN);
    }

    public static Date SNolineToD(String dateStr) {
        return SToD(dateStr, NOLINE_PATTERN);
    }

    private static Date SToD(String dateStr, String pattern) {
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }
        try {
            return getDateFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }


    //String to String
    public static String SNolineToSLine(String dateStr) {
        return DToSLine(SNolineToD(dateStr));
    }

    public static String SLineToSNoline(String dateStr) {
        return DToSNoline(SLineToD(dateStr));
    }


    public static void main(String[] args) {
        try {
            System.out.println(DToSLine(new SimpleDateFormat("yyyy-MM-dd").parse("2017-04-28")));
            System.out.println(DToSNoline(new SimpleDateFormat("yyyyMMdd").parse("20170428")));

            System.out.println(SLineToD("2017-04-28"));
            System.out.println(SNolineToD("20170428"));

            System.out.println(SNolineToSLine("20170428"));
            System.out.println(SLineToSNoline("2017-04-28"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    /********
     * 增加或减少指定时间
     *********/
    private static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    public static Date addYear(Date date, int yearAmount) {
        return addInteger(date, 1, yearAmount);
    }

    public static Date addMonth(Date date, int monthAmount) {
        return addInteger(date, 2, monthAmount);
    }

    public static Date addDay(Date date, int dayAmount) {
        return addInteger(date, 5, dayAmount);
    }

    public static Date addHour(Date date, int hourAmount) {
        return addInteger(date, 11, hourAmount);
    }

    public static Date addMinute(Date date, int minuteAmount) {
        return addInteger(date, 12, minuteAmount);
    }

    public static Date addSecond(Date date, int secondAmount) {
        return addInteger(date, 13, secondAmount);
    }


    /***********
     * 获取时间上指定
     ****************/
    private static int getInteger(Date date, int dateType) {
        int num = 0;
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            num = calendar.get(dateType);
        }
        return num;
    }

    public static int getYear(Date date) {
        return getInteger(date, 1);
    }

    public static int getMonth(Date date) {
        return getInteger(date, 2) + 1;
    }

    public static int getDay(Date date) {
        return getInteger(date, 5);
    }

    public static int getHour(Date date) {
        return getInteger(date, 11);
    }

    public static int getMinute(Date date) {
        return getInteger(date, 12);
    }

    public static int getSecond(Date date) {
        return getInteger(date, 13);
    }

    public static Date timeStampToDate(String time) {
        return null;

    }

    public static String getCurrentDateStr() {
        return format(LINE_PATTERN, new Date());
    }

    public static String getCurrentMonthFirstDayStr() {
        Date date = DateUtils.setDays(new Date(), 1);
        return format(LINE_PATTERN, date);
    }

    public static String getCurrentDayBefore(int days) {
        Date date = DateUtils.addDays(new Date(), days);
        return format(LINE_PATTERN, date);
    }

    public static String format(String pattern, Date date) {
        if (StringUtils.isBlank(pattern)) {
            pattern = LINE_PATTERN;
        }
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String formStringDate(String source, String sourcePattern, String targetPattern) throws ParseException {
        DateFormat format = new SimpleDateFormat(sourcePattern);
        Date date = format.parse(source);
        return format(targetPattern, date);
    }

    public static Date getCurrentDateTime() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(new Date());
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            logger.error("getCurrentDateTime 异常" + e);
            return null;
        }
    }

    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), TEMP_PATTERN);
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean compareCurrentDateTime(int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        Date appointDate = calendar.getTime();
        return currentDate.compareTo(appointDate) <= 0;
    }
}
