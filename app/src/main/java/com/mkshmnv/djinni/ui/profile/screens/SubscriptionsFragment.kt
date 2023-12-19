package com.mkshmnv.djinni.ui.profile.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentSubscriptionsBinding
import com.mkshmnv.djinni.repository.UserViewModel
import com.mkshmnv.djinni.ui.viewBinding

class SubscriptionsFragment : Fragment(R.layout.fragment_subscriptions) {
    private val binding: FragmentSubscriptionsBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()
    private var uiChangeListener: UiChangeListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", this::class.simpleName)

        binding.llSubsDisableAll.setOnClickListener {

        }

    }

    // Метод для встановлення слухача
    fun setUiChangeListener(listener: UiChangeListener) {
        uiChangeListener = listener
    }

    // Метод, який буде викликаний при зміні UI
    private fun notifyUiChanged() {
        uiChangeListener?.onUiChanged(true)
    }

    // Метод, який буде викликаний при відсутності змін в UI
    private fun notifyUiNotChanged() {
        uiChangeListener?.onUiChanged(false)
    }
}

interface UiChangeListener {
    fun onUiChanged(uiChanged: Boolean)
}