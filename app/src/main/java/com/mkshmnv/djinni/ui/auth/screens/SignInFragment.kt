package com.mkshmnv.djinni.ui.auth.screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentSignInBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private lateinit var binding: FragmentSignInBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        binding.apply {
            btnSignIn.setOnClickListener {
                findNavController().navigate(R.id.action_nav_auth_pager_fragment_to_nav_dashboard_web_view)
                onDestroyView()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }
}