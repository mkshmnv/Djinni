package com.mkshmnv.djinni.ui.account.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentSubscriptionsBinding
import com.mkshmnv.djinni.model.FragmentScreen
import com.mkshmnv.djinni.model.User
import com.mkshmnv.djinni.repository.UserViewModel
import com.mkshmnv.djinni.ui.viewBinding

class SubscriptionsFragment : Fragment(R.layout.fragment_subscriptions) {
    private val binding: FragmentSubscriptionsBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()

    // For logger
    private val tag = this::class.simpleName!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", this::class.simpleName)

        userViewModel.authorizedUser.observeForever {
            loadUserDataToUI(it)
        }

        binding.llSubsDisableAll.setOnClickListener {
            // TODO: impl fun
        }
    }

    override fun onPause() {
        saveUIUserData()
        super.onPause()
    }

    private fun loadUserDataToUI(currentUser: User) {
        Logger.logcat("loadUserDataToUI", tag)
        fun rbgSubsVacanciesActive(active: Boolean) {
            binding.apply {
                rbSubsVacanciesNotSend.isEnabled = !active
                rbSubsVacanciesEmail.isEnabled = !active
            }
        }
        binding.apply {
            rbgSubsVacancies.check(currentUser.accordingVacancies.toInt())

            rbgSubsNotifications.apply {
                check(currentUser.notificationsFromEmployers.toInt())
                rbgSubsVacanciesActive(rbSubsNotificationsTelegram.isChecked)
            }

            chbSubsReceiveOffers.isChecked = currentUser.automaticOffers
        }
    }

    private fun saveUIUserData() {
        binding.apply {
            val tempUser = User(
                accordingVacancies = rbgSubsVacancies.checkedRadioButtonId.toString(),
                notificationsFromEmployers = rbgSubsNotifications.checkedRadioButtonId.toString(),
                automaticOffers = chbSubsReceiveOffers.isChecked,
            )
            userViewModel.updateUserFromUI(
                screen = FragmentScreen.SUBSCRIPTIONS,
                uiUser = tempUser
            )
        }
    }
}