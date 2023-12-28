package com.mkshmnv.djinni.ui.account.screens

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentProfileBinding
import com.mkshmnv.djinni.model.FragmentScreen
import com.mkshmnv.djinni.model.User
import com.mkshmnv.djinni.repository.UserViewModel
import com.mkshmnv.djinni.setDropDownValuesExtWithCurrentPosition
import com.mkshmnv.djinni.ui.viewBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()
    private var currentUser: User = User(uid = "User is null")

    // For logger
    private val tag = this::class.simpleName!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentUser = userViewModel.authorizedUser.value ?: throw Exception("User is null")
        Logger.logcat("onViewCreated with user - $currentUser", tag)

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

        // Set user data to UI and listeners
        binding.apply {
            // Status search - Radio Button Group
            rbgStatusSearch.check(currentUser.profileStatus.toInt())

            // Raise profile - Button TODO: impl
            btnRaiseProfile.setOnClickListener {
                Logger.logcat("Button Raise Profile is clicked", tag)
            }

            // Position - Edit Text
            etProfilePosition.setText(currentUser.position)

            // Category - Spinner
            spProfileCategory.setDropDownValuesExtWithCurrentPosition(
                R.array.profile_categories,
                currentUser.category
            )

            // Skills - Edit Text
            etProfileSkills.setText(currentUser.skills)

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
                    }
                })
            }

            // Salary expectation - Edit Text
            etProfileSalary.setText(currentUser.salary)

            // Country of residence - Spinner
            spProfileCountry.apply {
                setDropDownValuesExtWithCurrentPosition(
                    R.array.profile_countries,
                    currentUser.country
                )
            }
            chbProfileOnline.isChecked = currentUser.online
            chbProfileLeaveCountry.isChecked = currentUser.leave
            chbProfileRelocation.isChecked = currentUser.relocation

            // City - Edit Text
            etProfileCity.setText(currentUser.city)
            chbProfileCityMoving.isChecked = currentUser.cityMoving

            // English level - Radio Button Group
            rbgProfileEnglishLevel.check(currentUser.englishLevel.toInt())

            // Experience description - Edit Text
            etProfileExperienceDescription.setText(currentUser.experienceDescription)

            // Achievements - Edit Text
            etProfileAchievements.setText(currentUser.achievements)

            // Expectation - Edit Text
            etProfileExpectation.setText(currentUser.expectation)
            chbProfileExpectationCombatant.isChecked = currentUser.expectationCombatant

            // Employment options - Check Boxes
            chbProfileEmploymentOptionsRemote.isChecked = currentUser.employmentOptionsRemote
            chbProfileEmploymentOptionsOffice.isChecked = currentUser.employmentOptionsOffice
            chbProfileEmploymentOptionsPartTime.isChecked = currentUser.employmentOptionsPartTime
            chbProfileEmploymentOptionsFreelance.isChecked = currentUser.employmentOptionsFreelance

            // Hourly rate
            etProfileHourlyRate.setText(currentUser.hourlyRate)

            // Not considering - Check Boxes
            // Domains
            chbProfileNotConsideringDomainsAdult.isChecked = currentUser.notConsideringDomainsAdult
            chbProfileNotConsideringDomainsGambling.isChecked =
                currentUser.notConsideringDomainsGambling
            chbProfileNotConsideringDomainsDating.isChecked =
                currentUser.notConsideringDomainsDating
            chbProfileNotConsideringDomainsGameDev.isChecked =
                currentUser.notConsideringDomainsGameDev
            chbProfileNotConsideringDomainsCrypto.isChecked =
                currentUser.notConsideringDomainsCrypto
            // Type of company
            chbProfileNotConsideringTypeCompanyAgency.isChecked =
                currentUser.notConsideringTypeCompanyAgency
            chbProfileNotConsideringTypeCompanyOutsource.isChecked =
                currentUser.notConsideringTypeCompanyOutsource
            chbProfileNotConsideringTypeCompanyOutStaff.isChecked =
                currentUser.notConsideringTypeCompanyOutStaff
            chbProfileNotConsideringTypeCompanyProduct.isChecked =
                currentUser.notConsideringTypeCompanyProduct
            chbProfileNotConsideringTypeCompanyStartUp.isChecked =
                currentUser.notConsideringTypeCompanyStartUp

            // Question for employer
            etProfileQuestionForEmployer.setText(currentUser.questionForEmployer)

            // Preferred language - Check Boxes
            chbProfilePreferredLanguageUkrainian.isChecked = currentUser.preferredLanguageUkrainian
            chbProfilePreferredLanguageEnglish.isChecked = currentUser.preferredLanguageEnglish

            // Spinner Preferred method of communication - Spinner
            spProfilePreferredCommunication.setDropDownValuesExtWithCurrentPosition(
                R.array.profile_methods,
                currentUser.preferredCommunication
            )

            // Delete account - Button
            tvDeleteAccount.setOnClickListener {
                Logger.logcat("Delete Account does not implement!", tag)
                // TODO Implement Delete Account fun
            }
        }
    }

    override fun onPause() {
        saveUIUserData()
        super.onPause()
    }

    private fun saveUIUserData() {
        binding.apply {
            currentUser = User(
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
        }
        userViewModel.updateUserFromUI(screen = FragmentScreen.PROFILE, uiUser = currentUser)
    }
}