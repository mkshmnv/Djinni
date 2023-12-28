package com.mkshmnv.djinni.ui.account

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mkshmnv.djinni.ui.dashboard.WebViewDashboardFragment
import com.mkshmnv.djinni.ui.account.screens.ContactsFragment
import com.mkshmnv.djinni.ui.account.screens.HiresFragment
import com.mkshmnv.djinni.ui.account.screens.ProfileFragment
import com.mkshmnv.djinni.ui.account.screens.StoplistFragment
import com.mkshmnv.djinni.ui.account.screens.SubscriptionsFragment

class AccountPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 4 // TODO implement hires

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfileFragment()
            1 -> ContactsFragment()
            2 -> SubscriptionsFragment()
            3 -> StoplistFragment()
            4 -> HiresFragment() // TODO implement hires
            else -> WebViewDashboardFragment()
        }
    }
}
