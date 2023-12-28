package com.mkshmnv.djinni.ui.account.screens

import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentStoplistBinding
import com.mkshmnv.djinni.model.FragmentScreen
import com.mkshmnv.djinni.model.User
import com.mkshmnv.djinni.repository.UserViewModel
import com.mkshmnv.djinni.ui.viewBinding

class StoplistFragment : Fragment(R.layout.fragment_stoplist) {
    private val binding: FragmentStoplistBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()

    // For logger
    private val tag = this::class.simpleName!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", this::class.simpleName)

        userViewModel.authorizedUser.observeForever {
            loadUserDataToUI(it)
        }

        // Save UI user when Drawer open
        val drawerLayout = requireActivity().findViewById<DrawerLayout>(R.id.drawer_layout)
        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerOpened(drawerView: View) {
                saveUIUserData()
            }

            override fun onDrawerClosed(drawerView: View) {
            }

            override fun onDrawerStateChanged(newState: Int) {
            }
        })
    }

    override fun onPause() {
        // Save UI user when screen on pause
        saveUIUserData()
        super.onPause()
    }

    // Set user data to UI
    private fun loadUserDataToUI(currentUser: User) {
        Logger.logcat("loadUserDataToUI", tag)
        binding.apply {
            etStoplistSearch.setText(currentUser.stopListSearch)
            etStoplistBlockCompanies.setText(currentUser.stopListBlockedCompanies)
            etStoplistBlockRecruiters.setText(currentUser.stopListBlockedRecruiters)
            etStoplistBlockVacancies.setText(currentUser.stopListBlockedVacancies)
        }
    }

    private fun saveUIUserData() {
        binding.apply {
            val tempUser = User(
                stopListSearch = etStoplistSearch.text.toString(),
                stopListBlockedCompanies = etStoplistBlockCompanies.text.toString(),
                stopListBlockedRecruiters = etStoplistBlockRecruiters.text.toString(),
                stopListBlockedVacancies = etStoplistBlockVacancies.text.toString(),
            )
            userViewModel.updateUserFromUI(FragmentScreen.STOP_LIST, uiUser = tempUser)
        }
    }
}