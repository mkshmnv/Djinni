package com.mkshmnv.djinni.ui.profile.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentSubscriptionsBinding
import com.mkshmnv.djinni.ui.viewBinding

class SubscriptionsFragment : Fragment(R.layout.fragment_subscriptions) {
    private val binding: FragmentSubscriptionsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", this::class.simpleName)

    }
}