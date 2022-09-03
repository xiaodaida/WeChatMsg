package org.example.timeutil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TimeUtils
 * @Description TODO
 * @date 2022/7/19 21:52
 * @Version 1.0
 * @Author liukai
 */
public class TimeUtils {
    private static final Logger logger = LoggerFactory.getLogger(TimeUtils.class);
    private static Map<Integer, String> WEEK_MAP = new HashMap<>();

    public static Integer getSysDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateStr = simpleDateFormat.format(date);
        Integer dateInt = Integer.parseInt(dateStr);
        return dateInt;
    }

    public static Integer getSysTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        String timeStr = simpleDateFormat.format(date);
        Integer timeInt = Integer.parseInt(timeStr);
        return timeInt;
    }

    public static Integer getSysDateTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateTime = simpleDateFormat.format(date);
        Integer dateTimeInt = Integer.parseInt(dateTime);
        return dateTimeInt;
    }

    public static String getSysDateStr() {
        try {
            Calendar calendar = Calendar.getInstance();
            String sysDate = StringUtils.EMPTY;
            sysDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
            return sysDate;
        } catch (Exception e) {
            logger.error("获取系统时间失败", e);
            return StringUtils.SPACE;
        }

    }

    public static String getSysWeek() {
        try {
            Calendar calendar = Calendar.getInstance();
            String sysWeek = StringUtils.EMPTY;
            sysWeek = WEEK_MAP.getOrDefault(calendar.get(Calendar.DAY_OF_WEEK), "星期一");
            return sysWeek;
        } catch (Exception e) {
            logger.error("获取系统时间失败", e);
            return StringUtils.SPACE;
        }
    }

    public static String getSysDateAndWeek() {
        String dateWeek = StringUtils.EMPTY;
        dateWeek = getSysDateStr() + StringUtils.SPACE + getSysWeek();
        return dateWeek;
    }

    static {
        WEEK_MAP.put(1, "星期日");
        WEEK_MAP.put(2, "星期一");
        WEEK_MAP.put(3, "星期二");
        WEEK_MAP.put(4, "星期三");
        WEEK_MAP.put(5, "星期四");
        WEEK_MAP.put(6, "星期五");
        WEEK_MAP.put(7, "星期六");
    }

}
