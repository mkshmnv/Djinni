package com.mkshmnv.djinni.ui.auth.screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentSignInBinding
import com.mkshmnv.djinni.ui.profile.repository.UserViewModel
import com.mkshmnv.djinni.ui.viewBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    // For logger
    private val tag = this::class.simpleName!!

    private val binding: FragmentSignInBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hide action bar for this fragment
        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        Logger.logcat("onViewCreated", tag)
        Logger.logcat("userViewModel - ${userViewModel}", tag)

        binding.apply {
            // TODO: TESTs remove
            etSignInEmail.setText("1@1.com")
            etSignInPassword.setText("111111")

            btnSignIn.setOnClickListener {
                val email = etSignInEmail.text.toString()
                val password = etSignInPassword.text.toString()
                userViewModel.apply {
                    signInUser(email = email, password = password)
                    authorizedUser.observe(viewLifecycleOwner) {
                        Logger.logcat("authorizedUser observe : $it", tag)
                        findNavController().navigate(R.id.nav_dashboard_web_view) // TODO: change to action nav_dashboard
                        (activity as? AppCompatActivity)?.supportActionBar?.show()
                    }
                }
            }
            btnSignInWithGoogle.setOnClickListener {
                userViewModel.signInWithGoogle()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Logger.logcat("onDestroyView", tag)
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }
}