package com.mkshmnv.djinni

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.mkshmnv.djinni.databinding.CustomViewWithTitleBinding

class CustomViewWithTitle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.title,
    defStyleRes: Int = R.style.CustomViewWithTitleStyle
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val binding: CustomViewWithTitleBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.custom_view_with_title, this, true)
        binding = CustomViewWithTitleBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, defStyleRes)
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.CustomViewWithTitle, defStyleAttr, defStyleRes
        )
        val tempTitle = typedArray.getString(R.styleable.CustomViewWithTitle_title)
        binding.tvTitle.text = tempTitle
        typedArray.recycle()
    }
}