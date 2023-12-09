package com.mkshmnv.djinni.ui.inbox

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentInboxBinding

class InboxFragment : Fragment(R.layout.fragment_inbox) {
    private lateinit var binding: FragmentInboxBinding// by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", this::class.simpleName)
        binding = FragmentInboxBinding.bind(view)
        // Ініціалізація TabLayout
        binding.tabLayout.apply {
            // TODO impl filters!
            addTab(newTab().setText(getString(R.string.inbox_filter_all)))
            addTab(newTab().setText(getString(R.string.inbox_filter_new)))
            addTab(newTab().setText(getString(R.string.inbox_filter_active)))
            addTab(newTab().setText(getString(R.string.inbox_filter_favorites)))
            addTab(newTab().setText(getString(R.string.inbox_filter_archive)))
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    // Код, який викликається при виборі таби
                    tab?.let {
                        val selectedTabIndex = it.position
                        Logger.logcat("Clicked $selectedTabIndex - ${tab.text}")
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    // Код, який викликається при відміні вибору таби
                    tab?.let {
                        // Робіть щось із табою, яка була вибрана, але тепер не вибрана
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    // Код, який викликається при повторному виборі вже вибраної таби
                    tab?.let {
                        // Робіть щось при повторному виборі таби
                    }
                }
            })
        }

    }
}