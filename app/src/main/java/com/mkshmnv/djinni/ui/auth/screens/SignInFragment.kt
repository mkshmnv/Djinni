package com.mkshmnv.djinni.ui.auth.screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentSignInBinding
import com.mkshmnv.djinni.ui.auth.AuthPagerViewModel
import com.mkshmnv.djinni.ui.viewBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private val binding: FragmentSignInBinding by viewBinding()
    private val viewModel: AuthPagerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hide action bar for this fragment
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        binding.apply {
            btnSignIn.setOnClickListener {
                viewModel.apply {
                    signIn(etSignInEmail.text.toString(), etSignInPassword.text.toString())
                    authSignInSuccess.observe(viewLifecycleOwner) {
                        findNavController().navigate(R.id.nav_dashboard_web_view) // TODO: change to nav_dashboard
                        onDestroyView()
                    }
                }
            }
            btnSignInWithGoogle.setOnClickListener {
                viewModel.signInWithGoogle()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }
}