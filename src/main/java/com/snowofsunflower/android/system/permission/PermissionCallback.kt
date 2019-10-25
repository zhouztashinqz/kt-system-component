package com.snowofsunflower.android.system.permission

import android.content.Context
import com.yanzhenjie.permission.RequestExecutor

/**
 * TODO 赋予默认空回调，避免需要无意义的覆盖方法
 */
interface PermissionCallback {
    fun onGranted(permissions: List<String>) {}
    fun onDenied(permissions: List<String>) {}
    fun showAwaysDeniedRationale(permissions: List<String>) {}
    fun showRationale(context: Context, permissions: List<String>, executor: RequestExecutor) {}
}