package com.mkshmnv.djinni.ui.auth.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.Toast
import com.mkshmnv.djinni.databinding.FragmentSignInBinding
import com.mkshmnv.djinni.repository.UserViewModel
import com.mkshmnv.djinni.ui.viewBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private val binding: FragmentSignInBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // TODO: TESTs remove
            etSignInEmail.setText("1@1.com")
            etSignInPassword.setText("111111")
            btnSignIn.setOnClickListener {
                val email = etSignInEmail.text.toString()
                val password = etSignInPassword.text.toString()
                showLoadingAnimation(true)
                userViewModel.signInUser(email = email, password = password) {
                    showLoadingAnimation(false)
                    val navController = findNavController()
                    navController.navigate(R.id.action_nav_auth_pager_fragment_to_nav_account_pager_fragment)
                }
            }
            btnSignInWithGoogle.setOnClickListener {
                userViewModel.signInWithGoogle()
            }
        }
    }

    private fun showLoadingAnimation(show: Boolean) {
        // TODO: impl showLoadingAnimation
        if (!show) Toast.show("Loading...")
    }
}