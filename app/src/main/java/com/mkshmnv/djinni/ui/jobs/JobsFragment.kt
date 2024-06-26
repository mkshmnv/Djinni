package com.mkshmnv.djinni.ui.jobs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentJobsBinding
import com.mkshmnv.djinni.ui.viewBinding

class JobsFragment : Fragment(R.layout.fragment_jobs) {
    private val binding: FragmentJobsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", this::class.simpleName)

    }
}