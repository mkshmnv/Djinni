package com.mkshmnv.djinni.ui.inbox

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentInboxBinding
import com.mkshmnv.djinni.ui.viewBinding

class InboxFragment : Fragment(R.layout.fragment_inbox) {
    private val binding: FragmentInboxBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", this::class.simpleName)

    }
}