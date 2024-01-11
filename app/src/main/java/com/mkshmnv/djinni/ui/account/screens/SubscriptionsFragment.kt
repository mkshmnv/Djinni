package com.mkshmnv.djinni.ui.account.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentSubscriptionsBinding
import com.mkshmnv.djinni.model.FragmentScreen
import com.mkshmnv.djinni.model.User
import com.mkshmnv.djinni.repository.UserViewModel
import com.mkshmnv.djinni.ui.viewBinding

class SubscriptionsFragment : Fragment(R.layout.fragment_subscriptions) {
    private val binding: FragmentSubscriptionsBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var user: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentUser = userViewModel.authorizedUser.value
            ?: throw NullPointerException("AuthorizedUser is null")
        user = User(
            subsVacancies = currentUser.subsVacancies,
            subsNotifications = currentUser.subsNotifications,
            subsAutoOffers = currentUser.subsAutoOffers
        )
        loadUserData()
        binding.apply {
            rbgSubsNotifications.setOnCheckedChangeListener { _, _ ->
                rbgSubsVacanciesActive(rbSubsNotificationsTelegram.isChecked)
            }

            llSubsDisableAll.setOnClickListener {
                // TODO: implement disable all
            }
        }
    }

    override fun onPause() {
        super.onPause()
        saveUserData()
    }

    private fun loadUserData() {
        binding.apply {
            rbgSubsVacancies.check(user.subsVacancies.toInt())
            rbgSubsNotifications.apply {
                check(user.subsNotifications.toInt())
                rbgSubsVacanciesActive(rbSubsNotificationsTelegram.isChecked)
            }
            chbSubsReceiveOffers.isChecked = user.subsAutoOffers
        }
    }

    private fun saveUserData() {
        binding.apply {
            val tempUser = User(
                subsVacancies = rbgSubsVacancies.checkedRadioButtonId.toString(),
                subsNotifications = rbgSubsNotifications.checkedRadioButtonId.toString(),
                subsAutoOffers = chbSubsReceiveOffers.isChecked,
            )
            userViewModel.updateUserFromUI(screen = FragmentScreen.SUBS, uiUser = tempUser)
        }
    }

    private fun rbgSubsVacanciesActive(active: Boolean) {
        binding.rbSubsVacanciesNotSend.isEnabled = !active
        binding.rbSubsVacanciesEmail.isEnabled = !active
    }
}