package com.snowofsunflower.android.system.permission

import android.content.Context
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission

object PermissionCompat {
    fun toPermissionSetting(context: Context) {
        val settingService = AndPermission.permissionSetting(context)
        settingService.execute()
    }

    fun transformToName(context: Context, vararg permission: String) = Permission.transformText(context, permission)

    fun transformToName(context: Context, permission: List<String>) = Permission.transformText(context, permission)
}
