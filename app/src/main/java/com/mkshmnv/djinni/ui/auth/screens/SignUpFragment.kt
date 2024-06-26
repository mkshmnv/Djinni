package com.mkshmnv.djinni.ui.auth.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentSignUpBinding
import com.mkshmnv.djinni.repository.UserViewModel
import com.mkshmnv.djinni.ui.viewBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private val binding: FragmentSignUpBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager: ViewPager2 = requireActivity().findViewById(R.id.view_pager)
        val tabLayout: TabLayout = requireActivity().findViewById(R.id.tab_layout)
        binding.apply {
            etSignUpEmail.setText("1@1.com")
            etSignUpPassword.setText("111111")
            etSignUpConfirmPassword.setText("111111")
            btnSignUp.setOnClickListener {
                userViewModel.signUpUser(
                    etEmail = etSignUpEmail,
                    etPass = etSignUpPassword,
                    etConfPass = etSignUpConfirmPassword
                ) {
                    val tabIndexToNavigate = 0
                    viewPager.currentItem = tabIndexToNavigate
                    tabLayout.getTabAt(tabIndexToNavigate)?.select()
                }
            }
        }
    }
}