package com.mkshmnv.djinni.ui.auth.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.repository.UserViewModel

class SignOutFragment : Fragment(R.layout.fragment_sign_out) {
    private val userViewModel: UserViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.signOutUser {
            Logger.logcat("User signed out")
            val navController = findNavController()
            navController.navigate(R.id.nav_auth_pager_fragment)
        }
    }
}