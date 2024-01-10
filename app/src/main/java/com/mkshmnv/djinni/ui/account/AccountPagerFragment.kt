package com.mkshmnv.djinni.ui.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentPagerBinding
import com.mkshmnv.djinni.ui.PagerAdapter
import com.mkshmnv.djinni.ui.account.screens.ContactsFragment
import com.mkshmnv.djinni.ui.account.screens.ProfileFragment
import com.mkshmnv.djinni.ui.account.screens.StoplistFragment
import com.mkshmnv.djinni.ui.account.screens.SubscriptionsFragment
import com.mkshmnv.djinni.ui.viewBinding

class AccountPagerFragment : Fragment(R.layout.fragment_pager) {
    private val binding: FragmentPagerBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabFragments = arrayOf(
            getString(R.string.tab_profile) to ProfileFragment(),
            getString(R.string.tab_cv) to ContactsFragment(),
            getString(R.string.tab_subs) to SubscriptionsFragment(),
            getString(R.string.tab_stoplist) to StoplistFragment(),
//            getString(R.string.tab_hires) to HiresFragment() // TODO implement hires
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