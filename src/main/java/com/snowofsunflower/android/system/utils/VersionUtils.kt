package com.snowofsunflower.android.system.utils

import android.content.Context
import android.content.pm.PackageManager

object VersionUtils {
    /**
     * 获取版本名称
     */
    fun getVersionName(context: Context): String {
        val pm = context.packageManager
        val pi = pm.getPackageInfo(context.packageName, PackageManager.GET_CONFIGURATIONS)
        return pi.versionName
    }

}