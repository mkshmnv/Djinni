package com.mkshmnv.djinni.ui.inbox

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayout
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentInboxBinding
import com.mkshmnv.djinni.repository.user.UserViewModel
import com.mkshmnv.djinni.ui.viewBinding

class InboxFragment : Fragment(R.layout.fragment_inbox) {
    private val binding: FragmentInboxBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()

    private val tag = this::class.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", tag)
        setTabLayout(binding.upperTabs)
    }

    private fun setTabLayout(tabs: TabLayout) {
        tabs.apply {
            // TODO impl filters!
            addTab(newTab().setText(getString(R.string.inbox_filter_all)))
            addTab(newTab().setText(getString(R.string.inbox_filter_new)))
            addTab(newTab().setText(getString(R.string.inbox_filter_active)))
            addTab(newTab().setText(getString(R.string.inbox_filter_favorites)))
            addTab(newTab().setText(getString(R.string.inbox_filter_archive)))
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.let {
                        val selectedTabIndex = it.position
                        Logger.logcat("Selected $selectedTabIndex - ${tab.text}")
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.let {
                        val unselectedTabIndex = it.position
                        Logger.logcat("Unselected $unselectedTabIndex - ${tab.text}")
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    tab?.let {
                        val reselectedTabIndex = it.position
                        Logger.logcat("Reselected $reselectedTabIndex - ${tab.text}")
                    }
                }
            })
        }
    }
}