package com.mkshmnv.djinni.ui.profile.screens

import android.os.Bundle
import android.view.MenuItem
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
import com.mkshmnv.djinni.setDropDownValuesExt
import com.mkshmnv.djinni.setOnCheckedChangeListenerExtSaveData
import com.mkshmnv.djinni.ui.viewBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var currentUser: User

    // For menu
    private var item: MenuItem? = null

    // For logger
    private val tag = this::class.simpleName!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentUser = userViewModel.authorizedUser.value ?: throw Exception("User is null")
        Logger.logcat("onViewCreated with user - $currentUser", tag)
        setUserDataToUI()
    }

    private fun setUserDataToUI() {
        binding.apply {
            // Status search - Radio Button Group
            rbgStatusSearch.apply {
                check(currentUser.profileStatus.toInt())
                setOnCheckedChangeListener { _, _ -> saveUserData() } // TODO: impl setOnCheckedChangeListenerExtSaveData
            }

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
                setDropDownValuesExt(R.array.profile_categories)
                setOnCheckedChangeListenerExtSaveData { saveUserData() }
            }

            // Skills - Edit Text
            etProfileSkills.apply {
                setText(currentUser.skills)
                setOnCheckedChangeListenerExtSaveData { saveUserData() }
            }

            // Experience // TODO: impl Experience
//            setSeekBarExperienceTerm(sbProfileExperience, tvProfileExperienceTerm)
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

            // Salary expectation
            etProfileSalary.setText(currentUser.salary)

            // Country of residence - Spinner
            spProfileCountry.apply {
                setDropDownValuesExt(R.array.profile_countries)
                setOnCheckedChangeListenerExtSaveData { saveUserData() }
            }
            chbProfileOnline.isChecked = currentUser.online
            chbProfileLeaveCountry.isChecked = currentUser.leave
            chbProfileRelocation.isChecked = currentUser.relocation

            // City
            etProfileCity.setText(currentUser.city)
            chbProfileCityMoving.isChecked = currentUser.cityMoving

            // English level
            rbgProfileEnglishLevel.check(currentUser.englishLevel.toInt())

            // Experience description
            etProfileExperienceDescription.setText(currentUser.experienceDescription)

            // Achievements
            etProfileAchievements.setText(currentUser.achievements)

            // Expectation
            etProfileExpectation.setText(currentUser.expectation)
            chbProfileExpectationCombatant.isChecked = currentUser.expectationCombatant

            // Employment options
            chbProfileEmploymentOptionsRemote.isChecked = currentUser.employmentOptionsRemote
            chbProfileEmploymentOptionsOffice.isChecked = currentUser.employmentOptionsOffice
            chbProfileEmploymentOptionsPartTime.isChecked = currentUser.employmentOptionsPartTime
            chbProfileEmploymentOptionsFreelance.isChecked = currentUser.employmentOptionsFreelance

            // Hourly rate
            etProfileHourlyRate.setText(currentUser.hourlyRate)

            // Not considering
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

            // Preferred language
            chbProfilePreferredLanguageUkrainian.isChecked = currentUser.preferredLanguageUkrainian
            chbProfilePreferredLanguageEnglish.isChecked = currentUser.preferredLanguageEnglish

            // Spinner Preferred method of communication
            spProfilePreferredCommunication.apply {
                setDropDownValuesExt(R.array.profile_methods)
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
    }
}