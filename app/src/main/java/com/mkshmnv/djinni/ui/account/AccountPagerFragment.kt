package com.mkshmnv.djinni.ui.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentPagerBinding
import com.mkshmnv.djinni.ui.viewBinding

class AccountPagerFragment : Fragment(R.layout.fragment_pager) {
    private val binding: FragmentPagerBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", this::class.simpleName)

        binding.apply {
            viewpager.adapter = AccountPagerAdapter(requireActivity())
            TabLayoutMediator(tabs, viewpager) { tab, position ->
                tab.text = when (position) {
                    0 -> getString(R.string.tab_profile)
                    1 -> getString(R.string.tab_cv)
                    2 -> getString(R.string.tab_subs)
                    3 -> getString(R.string.tab_stoplist)
                    4 -> getString(R.string.tab_hires) // TODO implement hires
                    else -> getString(R.string.error)
                }
            }.attach()
        }
    }
}