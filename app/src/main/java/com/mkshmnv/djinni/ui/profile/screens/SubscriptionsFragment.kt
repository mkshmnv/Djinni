package com.mkshmnv.djinni.ui.profile.screens

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
    private lateinit var currentUser: User
    // For logger
    private val tag = this::class.simpleName!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", this::class.simpleName)
        currentUser =
            userViewModel.authorizedUser.value ?: throw IllegalStateException("User is null")
        binding.apply {
            rbgSubsVacancies.apply {
                check(currentUser.accordingVacancies.toInt())
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }

            rbgSubsNotifications.apply {
                check(currentUser.notificationsFromEmployers.toInt())
                rbgSubsVacanciesActive(rbSubsNotificationsTelegram.isChecked)
                setOnCheckedChangeListener { _, _ ->
                    rbgSubsVacanciesActive(rbSubsNotificationsTelegram.isChecked)
                    saveUserData()
                }
            }

            chbSubsReceiveOffers.apply {
                isChecked = currentUser.automaticOffers
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }

            llSubsDisableAll.setOnClickListener {
                // TODO: impl fun
            }
        }
    }

    private fun rbgSubsVacanciesActive(active: Boolean) {
        binding.apply {
            rbSubsVacanciesNotSend.isEnabled = !active
            rbSubsVacanciesEmail.isEnabled = !active
        }
    }

    private fun saveUserData() {
        binding.apply {
            val temUIUser = User(
                accordingVacancies = rbgSubsVacancies.checkedRadioButtonId.toString(),
                notificationsFromEmployers = rbgSubsNotifications.checkedRadioButtonId.toString(),
                automaticOffers = chbSubsReceiveOffers.isChecked,
            )
            currentUser = temUIUser
            userViewModel.updateUserFromUI(
                screen = FragmentScreen.SUBSCRIPTIONS,
                uiUser = temUIUser
            )
        }
    }
}