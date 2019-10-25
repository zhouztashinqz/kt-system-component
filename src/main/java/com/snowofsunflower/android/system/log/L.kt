package com.snowofsunflower.android.system.log

import android.util.Log
import com.snowofsunflower.android.system.BuildConfig


/**
 * Created by Zhouztashin on 2015/7/14.
 * 日志类
 */
object L {


    var TAG = "TAG"
    val LOG_ENABLED = BuildConfig.DEBUG

    fun setDefaultTag(str: String) {
        TAG = str
    }

    /**
     * ---- Base ------
     */
    fun i(tag: String, o: Any) {
        if (LOG_ENABLED) {
            Log.i(tag, o.toString() + "")
        }
    }

    fun i(o: Any) {
        if (LOG_ENABLED) {
            Log.i(TAG, o.toString() + "")
        }

    }

    fun e(tag: String, o: Any) {
        if (LOG_ENABLED) {
            Log.e(tag, o.toString() + "")
        }
    }

    fun e(o: Any) {
        if (LOG_ENABLED) {
            Log.e(TAG, o.toString() + "")
        }

    }


    /**
     * log current call method.
     *
     * @param tag
     */
    fun iMethod(tag: String) {
        if (LOG_ENABLED) {
            val element = Thread.currentThread().stackTrace[3]
            Log.i(tag, element.methodName)
        }
    }

}
