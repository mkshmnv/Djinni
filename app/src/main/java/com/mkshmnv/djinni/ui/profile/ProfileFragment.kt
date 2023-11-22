package com.mkshmnv.djinni.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentProfileBinding
import com.mkshmnv.djinni.ui.viewBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()
    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", this::class.simpleName)

        binding.apply {
            ArrayAdapter.createFromResource(
                requireActivity(),
                R.array.categories,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spCategory.adapter = adapter
            }

            ArrayAdapter.createFromResource(
                requireActivity(),
                R.array.countries,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spCountry.adapter = adapter
            }

//        Preferred method of communication
            // TODO Implement method communication fun
            ArrayAdapter.createFromResource(
                requireActivity(),
                R.array.methods,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spPrefMethodComm.adapter = adapter
            }

//            UpdateProfile
            btnUpdateProfile.setOnClickListener {
                Logger.logcat("Update Profile does not implement!", this::class.simpleName)
                // TODO Implement Update Profile fun
            }

//            DeleteAccount
            tvDeleteAccount.setOnClickListener {
                Logger.logcat("Delete Account does not implement!", this::class.simpleName)
                // TODO Implement Delete Account fun
            }
        }
    }
}