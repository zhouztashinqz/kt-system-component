package com.snowofsunflower.android.system.log

/**
 * 扩展日志方法
 */
fun Any.logError(content: Any) {
    if (L.LOG_ENABLED) {
        val className = javaClass.simpleName
        L.e(className!!, content)
    }
}

fun Any.logInfo(content: Any) {
    if (L.LOG_ENABLED) {
        val className = javaClass.simpleName
        L.i(className!!, content)
    }
}