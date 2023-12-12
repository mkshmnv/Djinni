package com.mkshmnv.djinni.ui.profile.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentAccountBinding
import com.mkshmnv.djinni.ui.profile.repository.User
import com.mkshmnv.djinni.ui.profile.repository.UserViewModel
import com.mkshmnv.djinni.ui.viewBinding

// Contacts and CV
class AccountFragment : Fragment(R.layout.fragment_account) {
    // For logger
    private val tag = this::class.simpleName!!

    private val binding: FragmentAccountBinding by viewBinding()
    private val userViewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", tag)
        binding.apply {
            btnAccountSave.setOnClickListener {
                Logger.logcat("btnAccountSave", tag)
                val user = User(
                    fullName = etAccountFullName.text.toString(),
                    email = etAccountEmail.text.toString(),
                    skype = etAccountSkype.text.toString(),
                    phone = etAccountPhone.text.toString(),
                    telegram = etAccountTelegram.text.toString(),
                    whatsApp = etAccountWhatsapp.text.toString(),
                    linkedIn = etAccountLinkedin.text.toString(),
                    gitHub = etAccountGithub.text.toString(),
                    portfolio = etAccountPortfolio.text.toString(),
                    cv = etAccountCv.text.toString()
                )
                userViewModel.updateUserProfile(user)
            }
        }

        userViewModel.apply {
            getUserData()
            authorizedUser.observe(viewLifecycleOwner) { user ->
                Logger.logcat("user: $user", tag)
                if (user != null) updateUI(user)
            }
        }
    }

    private fun updateUI(user: User) {
        binding.apply {
            etAccountFullName.setText(user.fullName)
            etAccountEmail.setText(user.email)
            etAccountSkype.setText(user.skype)
            etAccountPhone.setText(user.phone)
            etAccountTelegram.setText(user.telegram)
            etAccountWhatsapp.setText(user.whatsApp)
            etAccountLinkedin.setText(user.linkedIn)
            etAccountGithub.setText(user.gitHub)
            etAccountPortfolio.setText(user.portfolio)
            etAccountCv.setText(user.cv)
        }
    }
}