package com.mkshmnv.djinni

import android.widget.Toast

object Toast {
    private val context = App.instance
    fun showWithLogger(text: String, tag: String = "") {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        Logger.logcat("Show Toast - $text", tag)
    }

    fun show(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}