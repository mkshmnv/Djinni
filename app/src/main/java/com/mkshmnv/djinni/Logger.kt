package com.mkshmnv.djinni

import android.util.Log

object Logger {
    fun logcat(message: String, tag: String? = "") {
        Log.d("Logger $tag", message)
    }
}
