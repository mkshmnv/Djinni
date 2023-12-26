package com.mkshmnv.djinni.ui.profile.screens

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentProfileBinding
import com.mkshmnv.djinni.model.FragmentScreen
import com.mkshmnv.djinni.model.User
import com.mkshmnv.djinni.repository.UserViewModel
import com.mkshmnv.djinni.setDropDownValuesExtWithCurrentPosition
import com.mkshmnv.djinni.setOnCheckedChangeListenerExtSaveData
import com.mkshmnv.djinni.ui.viewBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var currentUser: User

    // For logger
    private val tag = this::class.simpleName!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentUser = userViewModel.authorizedUser.value ?: throw Exception("User is null")
        Logger.logcat("onViewCreated with user - $currentUser", tag)

        // Set user data to UI and listeners
        binding.apply {
            // Status search - Radio Button Group
            rbgStatusSearch.apply {
                check(currentUser.profileStatus.toInt())
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }

            // Raise profile - Button TODO: impl
            btnRaiseProfile.setOnClickListener {
                Logger.logcat("Button Raise Profile is clicked", tag)
            }

            // Position - Edit Text
            etProfilePosition.apply {
                setText(currentUser.position)
                setOnCheckedChangeListenerExtSaveData { saveUserData() }
            }

            // Category - Spinner
            spProfileCategory.apply {
                setDropDownValuesExtWithCurrentPosition(R.array.profile_categories, currentUser.category)
                setOnCheckedChangeListenerExtSaveData { saveUserData() }
            }

            // Skills - Edit Text
            etProfileSkills.apply {
                setText(currentUser.skills)
                setOnCheckedChangeListenerExtSaveData { saveUserData() }
            }

            // Experience - Seek Bar
            sbProfileExperience.apply {
                progress = currentUser.experienceProgress
                setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?, progress: Int, fromUser: Boolean
                    ) {
                        tvProfileExperienceTerm.text = when (progress) {
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

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        saveUserData()
                    }
                })
            }

            // Salary expectation - Edit Text
            etProfileSalary.apply {
                setText(currentUser.salary)
                setOnCheckedChangeListenerExtSaveData { saveUserData() }
            }

            // Country of residence - Spinner
            spProfileCountry.apply {
                setDropDownValuesExtWithCurrentPosition(R.array.profile_countries, currentUser.country)
                setOnCheckedChangeListenerExtSaveData { saveUserData() }
            }
            chbProfileOnline.apply {
                isChecked = currentUser.online
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }
            chbProfileLeaveCountry.apply {
                isChecked = currentUser.leave
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }
            chbProfileRelocation.apply {
                isChecked = currentUser.relocation
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }

            // City - Edit Text
            etProfileCity.apply {
                setText(currentUser.city)
                setOnCheckedChangeListenerExtSaveData { saveUserData() }
            }
            chbProfileCityMoving.apply {
                isChecked = currentUser.cityMoving
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }

            // English level - Radio Button Group
            rbgProfileEnglishLevel.apply {
                check(currentUser.englishLevel.toInt())
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }

            // Experience description - Edit Text
            etProfileExperienceDescription.apply {
                setText(currentUser.experienceDescription)
                setOnCheckedChangeListenerExtSaveData { saveUserData() }
            }

            // Achievements - Edit Text
            etProfileAchievements.apply {
                setText(currentUser.achievements)
                setOnCheckedChangeListenerExtSaveData { saveUserData() }
            }

            // Expectation - Edit Text
            etProfileExpectation.apply {
                setText(currentUser.expectation)
                setOnCheckedChangeListenerExtSaveData { saveUserData() }
            }
            chbProfileExpectationCombatant.apply {
                isChecked = currentUser.expectationCombatant
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }

            // Employment options - Check Boxes
            chbProfileEmploymentOptionsRemote.apply {
                isChecked = currentUser.employmentOptionsRemote
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }
            chbProfileEmploymentOptionsOffice.apply {
                isChecked = currentUser.employmentOptionsOffice
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }
            chbProfileEmploymentOptionsPartTime.apply {
                isChecked = currentUser.employmentOptionsPartTime
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }
            chbProfileEmploymentOptionsFreelance.apply {
                isChecked = currentUser.employmentOptionsFreelance
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }

            // Hourly rate
            etProfileHourlyRate.apply {
                setText(currentUser.hourlyRate)
                setOnCheckedChangeListenerExtSaveData { saveUserData() }
            }

            // Not considering - Check Boxes
            // Domains
            chbProfileNotConsideringDomainsAdult.apply {
                isChecked = currentUser.notConsideringDomainsAdult
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }
            chbProfileNotConsideringDomainsGambling.apply {
                isChecked = currentUser.notConsideringDomainsGambling
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }
            chbProfileNotConsideringDomainsDating.apply {
                isChecked = currentUser.notConsideringDomainsDating
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }
            chbProfileNotConsideringDomainsGameDev.apply {
                isChecked = currentUser.notConsideringDomainsGameDev
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }
            chbProfileNotConsideringDomainsCrypto.apply {
                isChecked = currentUser.notConsideringDomainsCrypto
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }
            // Type of company
            chbProfileNotConsideringTypeCompanyAgency.apply {
                isChecked = currentUser.notConsideringTypeCompanyAgency
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }
            chbProfileNotConsideringTypeCompanyOutsource.apply {
                isChecked = currentUser.notConsideringTypeCompanyOutsource
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }
            chbProfileNotConsideringTypeCompanyOutStaff.apply {
                isChecked = currentUser.notConsideringTypeCompanyOutStaff
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }
            chbProfileNotConsideringTypeCompanyProduct.apply {
                isChecked = currentUser.notConsideringTypeCompanyProduct
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }
            chbProfileNotConsideringTypeCompanyStartUp.apply {
                isChecked = currentUser.notConsideringTypeCompanyStartUp
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }

            // Question for employer
            etProfileQuestionForEmployer.apply {
                setText(currentUser.questionForEmployer)
                setOnCheckedChangeListenerExtSaveData { saveUserData() }
            }

            // Preferred language - Check Boxes
            chbProfilePreferredLanguageUkrainian.apply {
                isChecked = currentUser.preferredLanguageUkrainian
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }
            chbProfilePreferredLanguageEnglish.apply {
                isChecked = currentUser.preferredLanguageEnglish
                setOnCheckedChangeListener { _, _ -> saveUserData() }
            }

            // Spinner Preferred method of communication - Spinner
            spProfilePreferredCommunication.apply {
                setDropDownValuesExtWithCurrentPosition(R.array.profile_methods, currentUser.preferredCommunication)
                setOnCheckedChangeListenerExtSaveData { saveUserData() }
            }

            // Delete account - Button
            tvDeleteAccount.setOnClickListener {
                Logger.logcat("Delete Account does not implement!", tag)
                // TODO Implement Delete Account fun
            }
        }
    }

    private fun saveUserData() {
        binding.apply {
            val temUIUser = User(
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
            currentUser = temUIUser
            userViewModel.updateUserFromUI(screen = FragmentScreen.PROFILE, uiUser = temUIUser)
        }
    }
}