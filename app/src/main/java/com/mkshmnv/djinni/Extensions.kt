package com.mkshmnv.djinni

import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import java.util.regex.Pattern

// String extensions
fun String.isEmail(): Boolean {
    val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$"
    val pattern = Pattern.compile(emailRegex)
    val matcher = pattern.matcher(this)
    return !matcher.matches()
}

// Spinner extensions
fun AppCompatSpinner.setPosition(resourcesStringArray: Int, positionFromDatabase: String) {
    val tempArray = context.resources.getStringArray(resourcesStringArray)
    val position = tempArray.indexOf(positionFromDatabase)
    ArrayAdapter.createFromResource(
        context, resourcesStringArray, android.R.layout.simple_spinner_item
    ).also { adapter ->
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        this.adapter = adapter
    }
    this.setSelection(position)
}