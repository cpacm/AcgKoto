package net.cpacm.acgkoto.utils;

import android.text.format.Time;

import java.util.Date;

/**
 * 时间类
 * Created by Lion on 2015/7/7.
 */
public class TimeUtils {

    private static final long MAXMILLS = 5 * 1000;
    private static long backMills = 0;

    /**
     * 获取天数
     *
     * @param mills
     * @return
     */
    public static int getDay(long mills) {
        int day = -1;
        if (mills > 0) {
            day = (int) (mills / 1000 / 60 / 60 / 24);
        }
        return day;
    }

    /**
     * 获取24制的小时数
     *
     * @param mills
     * @return
     */
    public static int getHour(long mills) {
        int hour = -1;
        if (mills > 0) {
            hour = (int) (mills / 1000 / 60 / 60) - getDay(mills) * 24;
        }
        return hour;
    }

    /**
     * 获取分数
     *
     * @param mills
     * @return
     */
    public static int getMinutes(long mills) {
        int min = -1;
        if (mills > 0) {
            min = (int) (mills / 1000 / 60) - getDay(mills) * 24 * 60 - getHour(mills) * 60;
        }
        return min;
    }

    public static boolean Back() {
        long time = System.currentTimeMillis() - backMills;
        if (time > MAXMILLS) {
            backMills = System.currentTimeMillis();
            return true;
        } else {
            backMills = System.currentTimeMillis();
            return false;
        }
    }

    public static String getAfterDate(int intervalDay) {
        Time time = new Time();
        long intervalMills = intervalDay * 24 * 60 * 60 * 1000;
        time.set(System.currentTimeMillis() + intervalMills);
        return time.format("%y/%m/%d");
    }

}
