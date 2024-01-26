package com.mkshmnv.djinni.ui.account.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.repository.user.UserViewModel

class DeleteAccountFragment : Fragment(R.layout.fragment_delete_account) {
    private val userViewModel: UserViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.deleteUser {
            Logger.logcat("User deleted")
            val navController = findNavController()
            navController.navigate(R.id.nav_auth_pager_fragment)
        }
    }
}