package com.mkshmnv.djinni.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentDashboardBinding
import com.mkshmnv.djinni.ui.viewBinding

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val binding: FragmentDashboardBinding by viewBinding()
    private val viewModel: DashboardViewModel by viewModels()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("Created", this::class.simpleName)
        binding.webViewTemp.apply {
            webViewClient = WebViewClient()
            val webSettings = settings
            webSettings.javaScriptEnabled = true
            loadUrl("https://djinni.co/my/dashboard/")
        }
    }
}