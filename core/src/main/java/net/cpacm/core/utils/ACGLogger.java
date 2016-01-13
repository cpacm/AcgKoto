package net.cpacm.core.utils;

import android.util.Log;

import net.cpacm.core.BuildConfig;


/**
 * Log帮助类
 * 请在发布时去掉注释
 * Created by Cpacm on 2015/5/15.
 */
public class ACGLogger {

    private static String TAG = "ACG";

    //TODO 修改debug的状态
    public static boolean DEBUG = BuildConfig.DEBUG;
    //public static boolean DEBUG = true;

    public static void setTag(String tag) {
        d("Changing log tag to %s", tag);
        TAG = tag;
        DEBUG = Log.isLoggable(TAG, Log.VERBOSE);
    }

    public static void d(String format, Object... args) {
        try {
            String msg = (args == null) ? format : String.format(format, args);
            if (DEBUG)
                Log.d(TAG, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void v(String format, Object... args) {
        try {
            String msg = (args == null) ? format : String.format(format, args);
            if (DEBUG)
                Log.v(TAG, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void e(String format, Object... args) {
        try {
            String msg = (args == null) ? format : String.format(format, args);
            if (DEBUG)
                Log.e(TAG, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wtf(String format, Object... args) {
        try {
            String msg = (args == null) ? format : String.format(format, args);
            if (DEBUG)
                Log.wtf(TAG, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
