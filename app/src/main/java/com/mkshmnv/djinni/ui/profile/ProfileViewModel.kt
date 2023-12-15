package com.mkshmnv.djinni.ui.profile

import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModel
import com.mkshmnv.djinni.App
import com.mkshmnv.djinni.R

class ProfileViewModel : ViewModel() {
    // For logger
    private val tag = this::class.simpleName!!
    private val context = App.instance

    fun setDropDownSpinner(spinner: AppCompatSpinner, arrayValues: Int) {
        ArrayAdapter.createFromResource(
            context, arrayValues, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    fun setSeekBarExperienceTerm(seekBar: SeekBar, textView: AppCompatTextView) {
        seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                textView.text = when (progress) {
                    0 -> context.getString(R.string.profile_experience_without)
                    1 -> context.getString(R.string.profile_experience_6_months)
                    2 -> context.getString(R.string.profile_experience_1_year)
                    3 -> context.getString(R.string.profile_experience_1_5_years)
                    4 -> context.getString(R.string.profile_experience_2_years)
                    5 -> context.getString(R.string.profile_experience_2_5_years)
                    6 -> context.getString(R.string.profile_experience_3_years)
                    7 -> context.getString(R.string.profile_experience_4_years)
                    8 -> context.getString(R.string.profile_experience_5_years)
                    9 -> context.getString(R.string.profile_experience_6_years)
                    10 -> context.getString(R.string.profile_experience_7_years)
                    11 -> context.getString(R.string.profile_experience_8_years)
                    12 -> context.getString(R.string.profile_experience_9_years)
                    13 -> context.getString(R.string.profile_experience_10_years)
                    14 -> context.getString(R.string.profile_experience_more_10_years)
                    else -> context.getString(R.string.profile_experience_without)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
}