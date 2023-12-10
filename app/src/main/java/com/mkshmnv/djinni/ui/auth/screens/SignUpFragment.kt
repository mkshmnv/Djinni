package com.mkshmnv.djinni.ui.auth.screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentSignUpBinding
import com.mkshmnv.djinni.ui.auth.AuthPagerViewModel
import com.mkshmnv.djinni.ui.viewBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private val binding: FragmentSignUpBinding by viewBinding()
    private val viewModel: AuthPagerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnSignUp.setOnClickListener {
                viewModel.apply {
                    signUp(etSignUpEmail.text.toString(), etSignUpPassword.text.toString())
                    authSignUpSuccess.observe(viewLifecycleOwner) {
                        findNavController().navigate(R.id.action_nav_auth_pager_fragment_to_nav_dashboard_web_view)
                        onDestroyView()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }
}