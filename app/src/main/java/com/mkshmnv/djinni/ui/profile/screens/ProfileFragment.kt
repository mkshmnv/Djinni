package com.mkshmnv.djinni.ui.profile.screens

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentProfileBinding
import com.mkshmnv.djinni.ui.profile.PagerViewModel
import com.mkshmnv.djinni.ui.viewBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()
    private val viewModel: PagerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", this::class.simpleName)

        binding.apply {
            sbProfileExperience.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    tvProfileExperienceTerm.text = when (seekBar?.progress) {
                        0 -> getString(R.string.profile_experience_without)
                        1 -> getString(R.string.profile_experience_6_months)
                        2 -> getString(R.string.profile_experience_1_year)
                        3 -> getString(R.string.profile_experience_1_5_years)
                        4 -> getString(R.string.profile_experience_2_years)
                        5 -> getString(R.string.profile_experience_2_5_years)
                        6 -> getString(R.string.profile_experience_3_years)
                        7 -> getString(R.string.profile_experience_4_years)
                        8 -> getString(R.string.profile_experience_5_years)
                        9 -> getString(R.string.profile_experience_6_years)
                        10 -> getString(R.string.profile_experience_7_years)
                        11 -> getString(R.string.profile_experience_8_years)
                        12 -> getString(R.string.profile_experience_9_years)
                        13 -> getString(R.string.profile_experience_10_years)
                        14 -> getString(R.string.profile_experience_more_10_years)
                        else -> getString(R.string.profile_error)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })

            ArrayAdapter.createFromResource(
                requireActivity(),
                R.array.profile_categories,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spProfileCategory.adapter = adapter
            }

            ArrayAdapter.createFromResource(
                requireActivity(),
                R.array.profile_countries,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spProfileCountry.adapter = adapter
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