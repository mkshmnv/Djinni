package com.mkshmnv.djinni

import android.content.Context
import android.widget.Toast

object Toast {
    fun showWithLogger(context: Context, text: String, tag: String = "") {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        Logger.logcat("Show Toast - $text", tag)
    }
    fun show(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}