package com.mkshmnv.djinni.ui.account.screens

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentProfileBinding
import com.mkshmnv.djinni.model.User
import com.mkshmnv.djinni.repository.UserViewModel
import com.mkshmnv.djinni.setPosition
import com.mkshmnv.djinni.ui.account.FragmentScreen
import com.mkshmnv.djinni.ui.viewBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var user: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataBaseUser = userViewModel.authorizedUser.value
            ?: throw NullPointerException("AuthorizedUser is null")
        user = User(
            profileStatus = dataBaseUser.profileStatus,
            profilePosition = dataBaseUser.profilePosition,
            profileCategory = dataBaseUser.profileCategory,
            profileSkills = dataBaseUser.profileSkills,
            profileExpProgress = dataBaseUser.profileExpProgress,
            profileSalary = dataBaseUser.profileSalary,
            profileCountry = dataBaseUser.profileCountry,
            profileOnline = dataBaseUser.profileOnline,
            profileLeave = dataBaseUser.profileLeave,
            profileRelocation = dataBaseUser.profileRelocation,
            profileCity = dataBaseUser.profileCity,
            profileCityMoving = dataBaseUser.profileCityMoving,
            profileEngLevel = dataBaseUser.profileEngLevel,
            profileExpDescription = dataBaseUser.profileExpDescription,
            profileAchievements = dataBaseUser.profileAchievements,
            profileExpectation = dataBaseUser.profileExpectation,
            profileCombatant = dataBaseUser.profileCombatant,
            profileOptionRemote = dataBaseUser.profileOptionRemote,
            profileOptionOffice = dataBaseUser.profileOptionOffice,
            profileOptionPartTime = dataBaseUser.profileOptionPartTime,
            profileOptionFreelance = dataBaseUser.profileOptionFreelance,
            profileHourlyRate = dataBaseUser.profileHourlyRate,
            profileNotAdult = dataBaseUser.profileNotAdult,
            profileNotGambling = dataBaseUser.profileNotGambling,
            profileNotDating = dataBaseUser.profileNotDating,
            profileNotGameDev = dataBaseUser.profileNotGameDev,
            profileNotCrypto = dataBaseUser.profileNotCrypto,
            profileNotAgency = dataBaseUser.profileNotAgency,
            profileNotOutsource = dataBaseUser.profileNotOutsource,
            profileNotOutStaff = dataBaseUser.profileNotOutStaff,
            profileNotProduct = dataBaseUser.profileNotProduct,
            profileNotStartUp = dataBaseUser.profileNotStartUp,
            profileQuesToEmployer = dataBaseUser.profileQuesToEmployer,
            profilePrefUkrainian = dataBaseUser.profilePrefUkrainian,
            profilePrefEnglish = dataBaseUser.profilePrefEnglish,
            profilePrefComm = dataBaseUser.profilePrefComm
        )

        loadUserData()

        binding.apply {
            // Raise profile - Button TODO: impl
            btnRaiseProfile.setOnClickListener {
                Logger.logcat("Button Raise Profile is clicked", this::class.simpleName!!)
            }
            // Delete Account - LL
            llDeleteAccount.setOnClickListener {
                // TODO: impl accept window delete account
                userViewModel.deleteUser {
                    val navController = findNavController()
                    navController.navigate(R.id.nav_auth_pager_fragment)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        saveUserData()
    }

    private fun loadUserData() {
        binding.apply {
            // Status search - Radio Button Group
            rbgStatusSearch.check(user.profileStatus.toInt())

            // Position - Edit Text
            etProfilePosition.setText(user.profilePosition)

            // Category - Spinner
            spProfileCategory.setPosition(R.array.profile_categories, user.profileCategory)

            // Skills - Edit Text
            etProfileSkills.setText(user.profileSkills)

            // Experience - Seek Bar
            sbProfileExperience.apply {
                progress = user.profileExpProgress
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

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
                })
            }

            // Salary expectation - Edit Text
            etProfileSalary.setText(user.profileSalary)

            // Country of residence - Spinner
            spProfileCountry.setPosition(R.array.profile_countries, user.profileCountry)
            chbProfileOnline.isChecked = user.profileOnline
            chbProfileLeaveCountry.isChecked = user.profileLeave
            chbProfileRelocation.isChecked = user.profileRelocation

            // City - Edit Text
            etProfileCity.setText(user.profileCity)
            chbProfileCityMoving.isChecked = user.profileCityMoving

            // English level - Radio Button Group
            rbgProfileEnglishLevel.check(user.profileEngLevel.toInt())

            // Experience description - Edit Text
            etProfileExperienceDescription.setText(user.profileExpDescription)

            // Achievements - Edit Text
            etProfileAchievements.setText(user.profileAchievements)

            // Expectation - Edit Text
            etProfileExpectation.setText(user.profileExpectation)
            chbProfileExpectationCombatant.isChecked = user.profileCombatant

            // Employment options - Check Boxes
            chbProfileEmploymentOptionsRemote.isChecked = user.profileOptionRemote
            chbProfileEmploymentOptionsOffice.isChecked = user.profileOptionOffice
            chbProfileEmploymentOptionsPartTime.isChecked = user.profileOptionPartTime
            chbProfileEmploymentOptionsFreelance.isChecked = user.profileOptionFreelance

            // Hourly rate
            etProfileHourlyRate.setText(user.profileHourlyRate)

            // Not considering - Check Boxes
            // Domains
            chbProfileNotConsideringDomainsAdult.isChecked = user.profileNotAdult
            chbProfileNotConsideringDomainsGambling.isChecked =
                user.profileNotGambling
            chbProfileNotConsideringDomainsDating.isChecked =
                user.profileNotDating
            chbProfileNotConsideringDomainsGameDev.isChecked =
                user.profileNotGameDev
            chbProfileNotConsideringDomainsCrypto.isChecked =
                user.profileNotCrypto
            // Type of company
            chbProfileNotConsideringTypeCompanyAgency.isChecked =
                user.profileNotAgency
            chbProfileNotConsideringTypeCompanyOutsource.isChecked =
                user.profileNotOutsource
            chbProfileNotConsideringTypeCompanyOutStaff.isChecked =
                user.profileNotOutStaff
            chbProfileNotConsideringTypeCompanyProduct.isChecked =
                user.profileNotProduct
            chbProfileNotConsideringTypeCompanyStartUp.isChecked =
                user.profileNotStartUp

            // Question for employer
            etProfileQuestionForEmployer.setText(user.profileQuesToEmployer)

            // Preferred language - Check Boxes
            chbProfilePreferredLanguageUkrainian.isChecked = user.profilePrefUkrainian
            chbProfilePreferredLanguageEnglish.isChecked = user.profilePrefEnglish

            // Spinner Preferred method of communication - Spinner
            spProfilePreferredCommunication.setPosition(
                R.array.profile_methods,
                user.profilePrefComm
            )
        }
    }

    private fun saveUserData() {
        binding.apply {
            // Get data from UI
            val tempUser = User(
                profileStatus = rbgStatusSearch.checkedRadioButtonId.toString(),
                profilePosition = etProfilePosition.text.toString(),
                profileCategory = spProfileCategory.selectedItem.toString(),
                profileSkills = etProfileSkills.text.toString(),
                profileExpProgress = sbProfileExperience.progress,
                profileSalary = etProfileSalary.text.toString(),
                profileCountry = spProfileCountry.selectedItem.toString(),
                profileOnline = chbProfileOnline.isChecked,
                profileLeave = chbProfileLeaveCountry.isChecked,
                profileRelocation = chbProfileRelocation.isChecked,
                profileCity = etProfileCity.text.toString(),
                profileCityMoving = chbProfileCityMoving.isChecked,
                profileEngLevel = rbgProfileEnglishLevel.checkedRadioButtonId.toString(),
                profileExpDescription = etProfileExperienceDescription.text.toString(),
                profileAchievements = etProfileAchievements.text.toString(),
                profileExpectation = etProfileExpectation.text.toString(),
                profileCombatant = chbProfileExpectationCombatant.isChecked,
                profileOptionRemote = chbProfileEmploymentOptionsRemote.isChecked,
                profileOptionOffice = chbProfileEmploymentOptionsOffice.isChecked,
                profileOptionPartTime = chbProfileEmploymentOptionsPartTime.isChecked,
                profileOptionFreelance = chbProfileEmploymentOptionsFreelance.isChecked,
                profileHourlyRate = etProfileHourlyRate.text.toString(),
                profileNotAdult = chbProfileNotConsideringDomainsAdult.isChecked,
                profileNotGambling = chbProfileNotConsideringDomainsGambling.isChecked,
                profileNotDating = chbProfileNotConsideringDomainsDating.isChecked,
                profileNotGameDev = chbProfileNotConsideringDomainsGameDev.isChecked,
                profileNotCrypto = chbProfileNotConsideringDomainsCrypto.isChecked,
                profileNotAgency = chbProfileNotConsideringTypeCompanyAgency.isChecked,
                profileNotOutsource = chbProfileNotConsideringTypeCompanyOutsource.isChecked,
                profileNotOutStaff = chbProfileNotConsideringTypeCompanyOutStaff.isChecked,
                profileNotProduct = chbProfileNotConsideringTypeCompanyProduct.isChecked,
                profileNotStartUp = chbProfileNotConsideringTypeCompanyStartUp.isChecked,
                profileQuesToEmployer = etProfileQuestionForEmployer.text.toString(),
                profilePrefUkrainian = chbProfilePreferredLanguageUkrainian.isChecked,
                profilePrefEnglish = chbProfilePreferredLanguageEnglish.isChecked,
                profilePrefComm = spProfilePreferredCommunication.selectedItem.toString()
            )
            // Check user data if some changing save it to DB
            if (tempUser != user && userViewModel.authorizedUser.value != null) {
                userViewModel.updateUserFromUI(FragmentScreen.PROFILE, uiUser = tempUser)
            }
        }
    }
}