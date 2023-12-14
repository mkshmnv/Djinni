package com.mkshmnv.djinni.ui.auth.screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentSignUpBinding
import com.mkshmnv.djinni.isEmail
import com.mkshmnv.djinni.ui.profile.repository.UserViewModel
import com.mkshmnv.djinni.ui.viewBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private val binding: FragmentSignUpBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnSignUp.setOnClickListener {
                val email = etSignUpEmail.text.toString()
                val pass = etSignUpPassword.text.toString()
                val confPass = etSignUpConfirmPassword.text.toString()
                when {
                    email.isEmpty() -> {
                        etSignUpEmail.error =
                            getString(R.string.auth_field_email_is_empty)
                    }

                    email.isEmail() -> {
                        etSignUpEmail.error =
                            getString(R.string.auth_email_is_not_valid)
                    }

                    pass.isEmpty() -> {
                        etSignUpPassword.error =
                            getString(R.string.auth_field_password_is_empty)
                    }

                    pass.length < 6 -> {
                        etSignUpPassword.error =
                            getString(R.string.auth_password_is_too_short)
                    }

                    confPass.isEmpty() -> {
                        etSignUpConfirmPassword.error =
                            getString(R.string.auth_field_confirm_password_is_empty)
                    }

                    confPass.length < 6 -> {
                        etSignUpConfirmPassword.error =
                            getString(R.string.auth_password_is_too_short)
                    }

                    pass != confPass -> {
                        etSignUpPassword.error =
                            getString(R.string.auth_passwords_do_not_match)
                        etSignUpConfirmPassword.error =
                            getString(R.string.auth_passwords_do_not_match)
                    }
                    // TODO: fix navigation
                    else -> userViewModel.signUpUser(email = email, password = pass)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }
}