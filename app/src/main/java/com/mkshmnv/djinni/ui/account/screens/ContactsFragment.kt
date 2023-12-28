package com.mkshmnv.djinni.ui.account.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentContactsBinding
import com.mkshmnv.djinni.model.FragmentScreen
import com.mkshmnv.djinni.model.User
import com.mkshmnv.djinni.repository.UserViewModel
import com.mkshmnv.djinni.ui.viewBinding

// Contacts and CV
class ContactsFragment : Fragment(R.layout.fragment_contacts) {
    private val binding: FragmentContactsBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()

    // For logger
    private val tag = this::class.simpleName!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentUser = userViewModel.authorizedUser.value ?: throw Exception("User is null")
        Logger.logcat("onViewCreated with user: $currentUser", tag)
        binding.apply {
            etAccountFullName.setText(currentUser.fullName)
            etAccountEmail.setText(currentUser.email)
            etAccountSkype.setText(currentUser.skype)
            etAccountPhone.setText(currentUser.phone)
            etAccountTelegram.setText(currentUser.telegram)
            etAccountWhatsapp.setText(currentUser.whatsApp)
            etAccountLinkedin.setText(currentUser.linkedIn)
            etAccountGithub.setText(currentUser.gitHub)
            etAccountPortfolio.setText(currentUser.portfolio)
            etAccountCv.setText(currentUser.cv)

            btnAccountSave.setOnClickListener {
                Logger.logcat("Button Save clicked", tag)
                userViewModel.updateUserFromUI(
                    screen = FragmentScreen.CONTACTS,
                    uiUser = User(
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
                )
            }
        }
    }
}