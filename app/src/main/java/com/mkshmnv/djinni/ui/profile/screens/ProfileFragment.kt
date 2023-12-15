package com.mkshmnv.djinni.ui.profile.screens

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentProfileBinding
import com.mkshmnv.djinni.model.FragmentScreen
import com.mkshmnv.djinni.model.User
import com.mkshmnv.djinni.repository.UserViewModel
import com.mkshmnv.djinni.ui.viewBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()

    // For logger
    private val tag = this::class.simpleName!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentUser = userViewModel.authorizedUser.value ?: throw Exception("User is null")
        Logger.logcat("onViewCreated with user - $currentUser", tag)
        binding.apply {
            etProfilePosition.setText(currentUser.position)
            ArrayAdapter.createFromResource(
                requireActivity(), R.array.profile_categories, android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spProfileCategory.adapter = adapter
            }


            sbProfileExperience.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    tvProfileExperienceTerm.text = setExperienceTerm(seekBar?.progress!!)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })



            ArrayAdapter.createFromResource(
                requireActivity(), R.array.profile_countries, android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spProfileCountry.adapter = adapter
            }

//          Preferred method of communication
            // TODO Implement method communication fun
            ArrayAdapter.createFromResource(
                requireActivity(), R.array.profile_methods, android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spProfilePreferredCommunication.adapter = adapter
            }

            btnUpdateProfile.setOnClickListener {
                Logger.logcat("Button Update Profile is clicked", tag)
                userViewModel.updateUserFromUI(
                    screen = FragmentScreen.PROFILE,
                    uiUser = User(
                        profileStatus = rbgStatusSearch.checkedRadioButtonId.toString(),
                        position = etProfilePosition.text.toString(),
                        category = spProfileCategory.selectedItem.toString(),
                        skills = etProfileSkills.text.toString(),
                        experienceProgress = sbProfileExperience.progress,
                        salary = etProfileSalary.text.toString(),
                        country = spProfileCountry.selectedItem.toString(),
                        online = chbProfileOnline.isChecked,
                        leave = chbProfileLeaveCountry.isChecked,
                        relocation = chbProfileRelocation.isChecked,
                        city = etProfileCity.text.toString(),
                        cityMoving = chbProfileCityMoving.isChecked,
                        englishLevel = rbgProfileEnglishLevel.checkedRadioButtonId.toString(),
                        experienceDescription = etProfileExperienceDescription.text.toString(),
                        achievements = etProfileAchievements.text.toString(),
                        expectation = etProfileExpectation.text.toString(),
                        expectationCombatant = chbProfileExpectationCombatant.isChecked,
                        employmentOptionsRemote = chbProfileEmploymentOptionsRemote.isChecked,
                        employmentOptionsOffice = chbProfileEmploymentOptionsOffice.isChecked,
                        employmentOptionsPartTime = chbProfileEmploymentOptionsPartTime.isChecked,
                        employmentOptionsFreelance = chbProfileEmploymentOptionsFreelance.isChecked,
                        hourlyRate = etProfileHourlyRate.text.toString(),
                        notConsideringDomainsAdult = chbProfileNotConsideringDomainsAdult.isChecked,
                        notConsideringDomainsGambling = chbProfileNotConsideringDomainsGambling.isChecked,
                        notConsideringDomainsDating = chbProfileNotConsideringDomainsDating.isChecked,
                        notConsideringDomainsGameDev = chbProfileNotConsideringDomainsGameDev.isChecked,
                        notConsideringDomainsCrypto = chbProfileNotConsideringDomainsCrypto.isChecked,
                        notConsideringTypeCompanyAgency = chbProfileNotConsideringTypeCompanyAgency.isChecked,
                        notConsideringTypeCompanyOutsource = chbProfileNotConsideringTypeCompanyOutsource.isChecked,
                        notConsideringTypeCompanyOutStaff = chbProfileNotConsideringTypeCompanyOutStaff.isChecked,
                        notConsideringTypeCompanyProduct = chbProfileNotConsideringTypeCompanyProduct.isChecked,
                        notConsideringTypeCompanyStartUp = chbProfileNotConsideringTypeCompanyStartUp.isChecked,
                        questionForEmployer = etProfileQuestionForEmployer.text.toString(),
                        preferredLanguageUkrainian = chbProfilePreferredLanguageUkrainian.isChecked,
                        preferredLanguageEnglish = chbProfilePreferredLanguageEnglish.isChecked,
                        preferredCommunication = spProfilePreferredCommunication.selectedItem.toString()
                    )
                )
            }

            tvDeleteAccount.setOnClickListener {
                Logger.logcat("Delete Account does not implement!", tag)
                // TODO Implement Delete Account fun
            }
        }
    }

    private fun setExperienceTerm(progress: Int): String {
        return when (progress) {
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
            else -> getString(R.string.profile_experience_without)
        }
    }
}