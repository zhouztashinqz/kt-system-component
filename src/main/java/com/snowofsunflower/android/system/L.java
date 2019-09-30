package com.snowofsunflower.android.system;

import android.util.Log;


/**
 * Created by Zhouztashin on 2015/7/14.
 * 日志类
 */
public class L {


    public static String TAG = "TAG";
    private static boolean LOG_ENABLED = BuildConfig.DEBUG;

    public static void setDefaultTag(String str) {
        TAG = str;
    }

    /**
     * ---- Base ------
     */
    public static void i(String tag, Object o) {
        if (LOG_ENABLED) {
            Log.i(tag, o + "");
        }
    }

    public static void i(Object o) {
        if (LOG_ENABLED) {
            Log.i(TAG, o + "");
        }

    }

    public static void e(String tag, Object o) {
        if (LOG_ENABLED) {
            Log.e(tag, o + "");
        }
    }

    public static void e(Object o) {
        if (LOG_ENABLED) {
            Log.e(TAG, o + "");
        }

    }


    /**
     * log current call method.
     *
     * @param tag
     */
    public static void iMethod(String tag) {
        if (LOG_ENABLED) {
            StackTraceElement element = Thread.currentThread().getStackTrace()[3];
            Log.i(tag, element.getMethodName());
        }
    }

}
