package com.mkshmnv.djinni.ui.account.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentStoplistBinding
import com.mkshmnv.djinni.model.FragmentScreen
import com.mkshmnv.djinni.model.User
import com.mkshmnv.djinni.repository.UserViewModel
import com.mkshmnv.djinni.ui.viewBinding

class StopListFragment : Fragment(R.layout.fragment_stoplist) {
    private val binding: FragmentStoplistBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var user: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentUser = userViewModel.authorizedUser.value
            ?: throw NullPointerException("AuthorizedUser is null")
        user = User(
            stopListSearch = currentUser.stopListSearch,
            stopListBlockCompanies = currentUser.stopListBlockCompanies,
            stopListBlockRecruiters = currentUser.stopListBlockRecruiters,
            stopListBlockVacancies = currentUser.stopListBlockVacancies
        )
        loadUserData()
    }

    override fun onPause() {
        super.onPause()
        saveUserData()
    }

    // Set user data to UI
    private fun loadUserData() {
        binding.apply {
            etStoplistSearch.setText(user.stopListSearch)
            etStoplistBlockCompanies.setText(user.stopListBlockCompanies)
            etStoplistBlockRecruiters.setText(user.stopListBlockRecruiters)
            etStoplistBlockVacancies.setText(user.stopListBlockVacancies)
        }
    }

    private fun saveUserData() {
        binding.apply {
            val tempUser = User(
                stopListSearch = etStoplistSearch.text.toString(),
                stopListBlockCompanies = etStoplistBlockCompanies.text.toString(),
                stopListBlockRecruiters = etStoplistBlockRecruiters.text.toString(),
                stopListBlockVacancies = etStoplistBlockVacancies.text.toString(),
            )
            if (tempUser != user) {
                userViewModel.updateUserFromUI(screen = FragmentScreen.STOP_LIST, uiUser = tempUser)
            }
        }
    }
}