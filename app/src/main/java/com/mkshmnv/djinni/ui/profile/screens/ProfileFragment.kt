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
import com.mkshmnv.djinni.ui.profile.repository.User
import com.mkshmnv.djinni.ui.profile.repository.UserViewModel
import com.mkshmnv.djinni.ui.viewBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()

    // For logger
    private val tag = this::class.simpleName!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.logcat("onViewCreated", tag)
        Logger.logcat("userViewModel - ${userViewModel}", tag)

        val user = userViewModel.authorizedUser.value // TODO: check null
        Logger.logcat("userViewModel.authorizedUser.value: $user", tag)
//        if (user != null) updateUI(user)


        binding.apply {
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
                R.array.profile_methods,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spProfilePreferredCommunication.adapter = adapter
            }

//            UpdateProfile
            btnUpdateProfile.setOnClickListener {
                Logger.logcat("Update Profile does not implement!", tag)
                // TODO Implement Update Profile fun
            }

//            DeleteAccount
            tvDeleteAccount.setOnClickListener {
                Logger.logcat("Delete Account does not implement!", tag)
                // TODO Implement Delete Account fun
            }
        }
    }

    private fun updateUI(user: User) {
        binding.apply {
            rbgStatusSearch.check(
                when (user.profileStatus) {
                    "ACTIVE" /* ProfileStatus.ACTIVE */ -> rbSearchActive.id
                    "PASSIVE" /* ProfileStatus.PASSIVE */ -> rbSearchPassive.id
                    else /*ProfileStatus.NOT_LOOKED */ -> rbSearchOff.id
                }
            )
            etProfilePosition.setText(user.position)
            spProfileCategory.setSelection(
                resources.getStringArray(R.array.profile_categories).indexOf(user.category)
            )
            etProfileSkills.setText(user.skills)
            sbProfileExperience.progress = user.experienceProgress
            tvProfileExperienceTerm.text = setExperienceTerm(user.experienceProgress)
            etProfileSalary.setText(user.salary)
            spProfileCountry.setSelection(
                resources.getStringArray(R.array.profile_countries).indexOf(user.city)
            )
            chbProfileOnline.isChecked = user.online
            chbProfileLeaveCountry.isChecked = user.leave
            chbProfileRelocation.isChecked = user.relocation
            etProfileCity.setText(user.city)
            chbProfileCityMoving.isChecked = user.cityMoving
            rbgProfileEnglishLevel.check(
                when (user.englishLevel) {
                    1 -> rbBeginnerEnglish.id
                    2 -> rbPreIntermediateEnglish.id
                    3 -> rbIntermediateEnglish.id
                    4 -> rbUpperIntermediateEnglish.id
                    5 -> rbAdvancedEnglish.id
                    else -> rbProfileNoEnglish.id
                }
            )
            etProfileExperienceDescription.setText(user.experienceDescription)
            etProfileAchievements.setText(user.achievements)
            etProfileExpectation.setText(user.expectation)
            chbProfileExpectationCombatant.isChecked = user.expectationCombatant
            chbProfileEmploymentOptionsRemote.isChecked = user.employmentOptionsRemote
            chbProfileEmploymentOptionsOffice.isChecked = user.employmentOptionsOffice
            chbProfileEmploymentOptionsPartTime.isChecked = user.employmentOptionsPartTime
            chbProfileEmploymentOptionsFreelance.isChecked = user.employmentOptionsFreelance
            etProfileHourlyRate.setText(user.hourlyRate)
            chbProfileNotConsideringDomainsAdult.isChecked = user.notConsideringDomainsAdult
            chbProfileNotConsideringDomainsGambling.isChecked = user.notConsideringDomainsGambling
            chbProfileNotConsideringDomainsDating.isChecked = user.notConsideringDomainsDating
            chbProfileNotConsideringDomainsGameDev.isChecked = user.notConsideringDomainsGameDev
            chbProfileNotConsideringDomainsCrypto.isChecked = user.notConsideringDomainsCrypto
            chbProfileNotConsideringTypeCompanyAgency.isChecked =
                user.notConsideringTypeCompanyAgency
            chbProfileNotConsideringTypeCompanyOutsource.isChecked =
                user.notConsideringTypeCompanyOutsource
            chbProfileNotConsideringTypeCompanyOutStaff.isChecked =
                user.notConsideringTypeCompanyOutStaff
            chbProfileNotConsideringTypeCompanyProduct.isChecked =
                user.notConsideringTypeCompanyProduct
            chbProfileNotConsideringTypeCompanyStartUp.isChecked =
                user.notConsideringTypeCompanyStartUp
            etProfileQuestionForEmployer.setText(user.questionForEmployer)
            chbProfilePreferredLanguageUkrainian.isChecked = user.preferredLanguageUkrainian
            chbProfilePreferredLanguageEnglish.isChecked = user.preferredLanguageEnglish
            spProfilePreferredCommunication.setSelection(
                resources.getStringArray(R.array.profile_methods)
                    .indexOf(user.preferredCommunication)
            )
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