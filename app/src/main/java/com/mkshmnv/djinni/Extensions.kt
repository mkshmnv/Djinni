package com.mkshmnv.djinni

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import java.util.regex.Pattern

// String extensions
fun String.isEmail(): Boolean {
    val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$"
    val pattern = Pattern.compile(emailRegex)
    val matcher = pattern.matcher(this)
    return !matcher.matches()
}

// EditText extension
fun AppCompatEditText.setOnCheckedChangeListenerExtSaveData(saveUser: () -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(
            charSequence: CharSequence?, start: Int, count: Int, after: Int
        ) {
        }

        override fun onTextChanged(
            charSequence: CharSequence?, start: Int, before: Int, count: Int
        ) {
        }

        override fun afterTextChanged(editable: Editable?) {
            saveUser()
        }
    })
}

// Spinner extensions
fun AppCompatSpinner.setDropDownValuesExtWithCurrentPosition(resourcesStringArray: Int, positionFromDatabase: String) {
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

fun AppCompatSpinner.setOnCheckedChangeListenerExtSaveData(saveUser: () -> Unit) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parentView: AdapterView<*>,
            selectedItemView: View?,
            position: Int,
            id: Long
        ) {
            saveUser()
        }

        override fun onNothingSelected(parentView: AdapterView<*>) {}
    }
}