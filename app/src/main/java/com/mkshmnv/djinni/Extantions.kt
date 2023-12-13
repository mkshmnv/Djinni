package com.mkshmnv.djinni

import java.util.regex.Pattern

private val context = App.instance

fun String.isEmail(): Boolean {
    val emailRegex = context.getString(R.string.email_regex)
    val pattern = Pattern.compile(emailRegex)
    val matcher = pattern.matcher(this)
    return !matcher.matches()
}