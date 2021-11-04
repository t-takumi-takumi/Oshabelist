package com.outside.oshabelist.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object AppUiUtils {
    /**
     * IMEを開く
     */
    fun openInputMethodManager(view: View?, delayMillis: Int) {
        view?.postDelayed(
            {
                view.requestFocus()
                val manager =
                    view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.showSoftInput(view, 0)
            }, delayMillis.toLong()
        )
    }
}