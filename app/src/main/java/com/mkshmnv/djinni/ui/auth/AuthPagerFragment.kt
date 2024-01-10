package com.mkshmnv.djinni.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentPagerBinding
import com.mkshmnv.djinni.ui.PagerAdapter
import com.mkshmnv.djinni.ui.auth.screens.SignInFragment
import com.mkshmnv.djinni.ui.auth.screens.SignUpFragment
import com.mkshmnv.djinni.ui.viewBinding

class AuthPagerFragment : Fragment(R.layout.fragment_pager) {
    private val binding: FragmentPagerBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", this::class.simpleName)

        val tabFragments = arrayOf(
            getString(R.string.auth_sign_in) to SignInFragment(),
            getString(R.string.auth_sign_up) to SignUpFragment()
        )

        val pagerAdapter = PagerAdapter(this, tabFragments)

        binding.apply {
            viewPager.adapter = pagerAdapter
            viewPager.offscreenPageLimit = tabFragments.size
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabFragments[position].first
            }.attach()
        }
    }
}