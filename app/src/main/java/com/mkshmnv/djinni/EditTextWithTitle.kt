package com.mkshmnv.djinni

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.mkshmnv.djinni.databinding.EditTextWithTitleBinding

class EditTextWithTitle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.title,
    defStyleRes: Int = R.style.EditTextWithTitleStyle
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val binding: EditTextWithTitleBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.edit_text_with_title, this, true)
        binding = EditTextWithTitleBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, defStyleRes)
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.EditTextWithTitle, defStyleAttr, defStyleRes
        )
        binding.apply {
            val title = typedArray.getString(R.styleable.EditTextWithTitle_title)
            tvTitle.text = title

            val hint = typedArray.getString(R.styleable.EditTextWithTitle_hint)
            etHint.hint = hint

            val inputType = typedArray.getInt(R.styleable.EditTextWithTitle_inputType, 0x00000001)
            etHint.inputType = inputType
        }
        typedArray.recycle()
    }
}