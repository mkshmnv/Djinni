package com.mkshmnv.djinni.ui.profile.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentProfileBinding
import com.mkshmnv.djinni.model.FragmentScreen
import com.mkshmnv.djinni.model.User
import com.mkshmnv.djinni.repository.UserViewModel
import com.mkshmnv.djinni.ui.MainActivity
import com.mkshmnv.djinni.ui.profile.ProfileViewModel
import com.mkshmnv.djinni.ui.viewBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()
    private val profileViewModel: ProfileViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var currentUser: User

    // For menu
    private var item: MenuItem? = null

    // For logger
    private val tag = this::class.simpleName!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        @Suppress("DEPRECATION")
        setHasOptionsMenu(true)
        currentUser = userViewModel.authorizedUser.value ?: throw Exception("User is null")
        Logger.logcat("onViewCreated with user - $currentUser", tag)
        binding.apply {
            // Status search
            rbgStatusSearch.apply {
                check(currentUser.profileStatus.toInt())
                setOnCheckedChangeListener { _, newProfileStatus ->
                    Logger.logcat("Status search is changed", this@ProfileFragment.tag)
                    checkState(newProfileStatus, currentUser.profileStatus.toInt())
                }
            }

            // Buttons ViewProfile and RaiseProfile
            btnViewProfile.setOnClickListener {
                Logger.logcat("Button View Profile is clicked", tag)
            }
            btnRaiseProfile.setOnClickListener {
                Logger.logcat("Button View Profile is clicked", tag)
            }

            // Position
            etProfilePosition.apply {
                setText(currentUser.position)
                addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        charSequence: CharSequence?, start: Int, count: Int, after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        charSequence: CharSequence?, start: Int, before: Int, count: Int
                    ) {
                    }

                    override fun afterTextChanged(editable: Editable?) {
                        val newText = editable.toString()
                        Logger.logcat("EditText is changed", this@ProfileFragment.tag)
                        checkState(newText, currentUser.position)
                    }
                })
            }

            // Spinner Category
            // TODO: impl setDropDownSpinner for Country
            profileViewModel.setDropDownSpinner(spProfileCategory, R.array.profile_categories)

            // Skills
            etProfileSkills.apply {
                setText(currentUser.skills)
                addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        charSequence: CharSequence?, start: Int, count: Int, after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        charSequence: CharSequence?, start: Int, before: Int, count: Int
                    ) {
                    }

                    override fun afterTextChanged(editable: Editable?) {
                        val newText = editable.toString()
                        Logger.logcat("EditText is changed", this@ProfileFragment.tag)
                        checkState(newText, currentUser.skills)
                    }
                })
            }

            // Experience // TODO: impl Experience
            profileViewModel.setSeekBarExperienceTerm(sbProfileExperience, tvProfileExperienceTerm)

            // Salary expectation
            etProfileSalary.setText(currentUser.salary)

            // Spinner Country of residence
            // TODO: impl setDropDownSpinner for Country
            profileViewModel.setDropDownSpinner(spProfileCountry, R.array.profile_countries)
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
            // TODO: impl setDropDownSpinner for Country
            profileViewModel.setDropDownSpinner(
                spProfilePreferredCommunication,
                R.array.profile_methods
            )

            // Button update profile
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

            // Button delete account
            tvDeleteAccount.setOnClickListener {
                Logger.logcat("Delete Account does not implement!", tag)
                // TODO Implement Delete Account fun
            }
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar_menu, menu)
        this.item = menu.findItem(R.id.save)
        this.item?.setVisible(false)
        @Suppress("DEPRECATION")
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.save) {
            Logger.logcat("${item.title} Clicked", tag)
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
                userViewModel.updateUserFromUI(
                    screen = FragmentScreen.PROFILE,
                    uiUser = currentUser
                )
            }
            this.item?.setVisible(false)
        }
        @Suppress("DEPRECATION")
        return super.onOptionsItemSelected(item)
    }

    private fun checkState(newData: Any, userData: Any) {
        if (newData != userData) {
            item?.setVisible(true)
            showButtonSave(true)
        } else {
            item?.setVisible(false)
            showButtonSave(false)
        }
    }

    private fun showButtonSave(show: Boolean) {
        val mainActivity = activity as? MainActivity
        mainActivity?.showSaveButton(show)
    }
}