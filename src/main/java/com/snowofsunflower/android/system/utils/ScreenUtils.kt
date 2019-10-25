package com.snowofsunflower.android.system.utils

import android.content.Context
import android.os.Build
import android.os.PowerManager
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * 屏幕工具类
 */
class ScreenUtils {
    companion object {
        /**
         * 屏幕宽度
         */
        fun screenWidth(context: Context): Int {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val outMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(outMetrics)
            return outMetrics.widthPixels
        }

        /**
         * 屏幕高度
         */
        fun screenHeight(context: Context): Int {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val outMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(outMetrics)
            return outMetrics.heightPixels
        }

        /**
         * DP to Px
         */
        fun dp2Px(context: Context, dpValue: Float): Float = context.resources.displayMetrics.density * dpValue

        /**
         * Px to Dp
         */
        fun px2Dp(context: Context, pxValue: Float): Float = pxValue / context.resources.displayMetrics.density

        /**
         * 屏幕密度
         */
        fun dp(context: Context): Int = context.resources.displayMetrics.densityDpi

        /**
         * 屏幕是否点亮
         */
        fun isScreenOn(context: Context): Boolean {
            val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
                return pm.isInteractive
            } else {
                return pm.isScreenOn
            }
        }
    }


}