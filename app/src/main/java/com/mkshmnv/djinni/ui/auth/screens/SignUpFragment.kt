package com.mkshmnv.djinni.ui.auth.screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentSignUpBinding
import com.mkshmnv.djinni.ui.profile.repository.UserViewModel
import com.mkshmnv.djinni.ui.viewBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private val binding: FragmentSignUpBinding by viewBinding()
    private val userViewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnSignUp.setOnClickListener {
                val email = etSignUpEmail.text.toString()
                val pass = etSignUpPassword.text.toString() // TODO: add confirm password
                userViewModel.apply {
                    signUpUser(email = email, password = pass)
                    authorizedUser.observe(viewLifecycleOwner) {
                        findNavController().navigate(R.id.nav_dashboard_web_view) // TODO: change to action nav_dashboard
                        (activity as? AppCompatActivity)?.supportActionBar?.show()
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