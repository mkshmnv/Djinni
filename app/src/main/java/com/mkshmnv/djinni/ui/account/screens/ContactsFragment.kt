package com.mkshmnv.djinni.ui.account.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentContactsBinding
import com.mkshmnv.djinni.model.user.User
import com.mkshmnv.djinni.repository.user.UserViewModel
import com.mkshmnv.djinni.ui.account.FragmentScreen
import com.mkshmnv.djinni.ui.viewBinding

class ContactsFragment : Fragment(R.layout.fragment_contacts) {
    private val binding: FragmentContactsBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var user: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentUser = userViewModel.authorizedUser.value
            ?: throw NullPointerException("AuthorizedUser is null")
        user = User(
            contactsFullName = currentUser.contactsFullName,
            contactsEmail = currentUser.contactsEmail,
            contactsSkype = currentUser.contactsSkype,
            contactsPhone = currentUser.contactsPhone,
            contactsTelegram = currentUser.contactsTelegram,
            contactsWhatsApp = currentUser.contactsWhatsApp,
            contactsLinkedIn = currentUser.contactsLinkedIn,
            contactsGitHub = currentUser.contactsGitHub,
            contactsPortfolio = currentUser.contactsPortfolio,
            contactsCV = currentUser.contactsCV
        )
        loadUserData()
    }

    override fun onPause() {
        super.onPause()
        saveUserData()
    }

    private fun loadUserData() {
        binding.apply {
            etAccountFullName.setText(user.contactsFullName)
            etAccountEmail.setText(user.contactsEmail)
            etAccountSkype.setText(user.contactsSkype)
            etAccountPhone.setText(user.contactsPhone)
            etAccountTelegram.setText(user.contactsTelegram)
            etAccountWhatsapp.setText(user.contactsWhatsApp)
            etAccountLinkedin.setText(user.contactsLinkedIn)
            etAccountGithub.setText(user.contactsGitHub)
            etAccountPortfolio.setText(user.contactsPortfolio)
            etAccountCv.setText(user.contactsCV)
        }
    }

    private fun saveUserData() {
        binding.apply {
            val tempUser = User(
                contactsFullName = etAccountFullName.text.toString(),
                contactsEmail = etAccountEmail.text.toString(),
                contactsSkype = etAccountSkype.text.toString(),
                contactsPhone = etAccountPhone.text.toString(),
                contactsTelegram = etAccountTelegram.text.toString(),
                contactsWhatsApp = etAccountWhatsapp.text.toString(),
                contactsLinkedIn = etAccountLinkedin.text.toString(),
                contactsGitHub = etAccountGithub.text.toString(),
                contactsPortfolio = etAccountPortfolio.text.toString(),
                contactsCV = etAccountCv.text.toString()
            )
            if (tempUser != user) {
                userViewModel.updateUserFromUI(screen = FragmentScreen.CONTACTS, uiUser = tempUser)
            }
        }
    }
}