package com.mkshmnv.djinni.ui.auth

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mkshmnv.djinni.ui.auth.screens.SignInFragment
import com.mkshmnv.djinni.ui.auth.screens.SignUpFragment
import com.mkshmnv.djinni.ui.dashboard.WebViewDashboardFragment

class AuthPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SignInFragment()
            1 -> SignUpFragment()
            else -> WebViewDashboardFragment() // TODO need error screen
        }
    }
}
