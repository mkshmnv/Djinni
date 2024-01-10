package com.mkshmnv.djinni.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(
    fragment: Fragment,
    private val fragments: Array<Pair<String, Fragment>>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int) = fragments[position].second
}