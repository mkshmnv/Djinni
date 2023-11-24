package com.mkshmnv.djinni.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mkshmnv.djinni.ui.dashboard.WebViewDashboardFragment
import com.mkshmnv.djinni.ui.profile.screens.AccountFragment
import com.mkshmnv.djinni.ui.profile.screens.HiresFragment
import com.mkshmnv.djinni.ui.profile.screens.ProfileFragment
import com.mkshmnv.djinni.ui.profile.screens.StoplistFragment
import com.mkshmnv.djinni.ui.profile.screens.SubscriptionsFragment

class PagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfileFragment()
            1 -> AccountFragment()
            2 -> SubscriptionsFragment()
            3 -> StoplistFragment()
            4 -> HiresFragment()
            else -> WebViewDashboardFragment()
        }
    }
}
