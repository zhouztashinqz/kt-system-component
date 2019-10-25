package com.snowofsunflower.android.system.permission

import android.app.Activity
import com.yanzhenjie.permission.AndPermission

/**
 * 检查权限
 */
object EnjoyPermission {
    fun checkPermission(activity: Activity, callback: PermissionCallback, vararg permissions: String) {
        AndPermission.with(activity).permission(permissions).onGranted {
            callback.onGranted(it)
        }.onDenied {
            if (AndPermission.hasAlwaysDeniedPermission(activity, it)) {
                callback.showAwaysDeniedRationale(it)
            } else {
                callback.onDenied(it)
            }
        }.rationale { context, permissions, executor ->
            callback.showRationale(context, permissions, executor)
        }.start()
    }
}
