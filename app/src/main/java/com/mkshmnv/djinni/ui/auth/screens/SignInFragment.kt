package com.mkshmnv.djinni.ui.auth.screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentSignInBinding
import com.mkshmnv.djinni.ui.profile.repository.UserViewModel
import com.mkshmnv.djinni.ui.viewBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private val binding: FragmentSignInBinding by viewBinding()
    private val userViewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hide action bar for this fragment
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        binding.apply {
            // TODO: TESTs remove
//            etSignInEmail.setText("asd@asd.com")
            etSignInEmail.setText("555@55.com")
//            etSignInPassword.setText("asdasd")
            etSignInPassword.setText("555555")

            btnSignIn.setOnClickListener {
                val email = etSignInEmail.text.toString()
                val pass = etSignInPassword.text.toString() // TODO: add confirm password
                userViewModel.apply {
                    signInUser(email = email, password = pass)
                    authorizedUser.observe(viewLifecycleOwner) {
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
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }
}