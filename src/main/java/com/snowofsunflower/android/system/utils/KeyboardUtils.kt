package com.snowofsunflower.android.system.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * 控制软键盘显示隐藏
 * Created by zengq on 2018/4/4.
 */

object KeyboardUtils {
    fun showKeyboard(view: View) {
        val imm = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null) {
            view.requestFocus()
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun hideKeyboard(view: View) {
        val imm = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
