package com.mkshmnv.djinni.repository

import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkshmnv.djinni.App
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.Toast
import com.mkshmnv.djinni.isEmail
import com.mkshmnv.djinni.model.FragmentScreen
import com.mkshmnv.djinni.model.User
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    // For logger
    private val tag = this::class.simpleName!!
    private val context = App.instance

    // Get repository
    private val userRepository = UserRepository()

    // LiveData
    private val _authorizedUser = MutableLiveData<User?>()
    val authorizedUser: LiveData<User?> = _authorizedUser

    init {
        Logger.logcat("UserViewModel init - $this", tag)
    }

    fun signUpUser(
        etEmail: AppCompatEditText, etPass: AppCompatEditText, etConfPass: AppCompatEditText
    ) {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confPass = etConfPass.text.toString()
        when {
            email.isEmpty() -> {
                etEmail.error = context.getString(R.string.auth_field_email_is_empty)
            }

            email.isEmail() -> {
                etEmail.error = context.getString(R.string.auth_email_is_not_valid)
            }

            pass.isEmpty() -> {
                etPass.error = context.getString(R.string.auth_field_password_is_empty)
            }

            pass.length < 6 -> {
                etPass.error = context.getString(R.string.auth_password_is_too_short)
            }

            confPass.isEmpty() -> {
                etConfPass.error = context.getString(R.string.auth_field_confirm_password_is_empty)
            }

            confPass.length < 6 -> {
                etConfPass.error = context.getString(R.string.auth_password_is_too_short)
            }

            pass != confPass -> {
                etPass.error = context.getString(R.string.auth_passwords_do_not_match)
                etConfPass.error = context.getString(R.string.auth_passwords_do_not_match)
            }

            else -> {
                viewModelScope.launch {
                    val result = userRepository.registrationUser(email, pass)
                    when (result) {
                        is Resource.Success -> {
                            signInUser(email, pass)
                            Toast.showWithLogger(
                                context.getString(R.string.auth_welcome_to_djinni),
                                "$tag signUpUser"
                            )
                        }

                        is Resource.Error -> {
                            Toast.showWithLogger(result.message, "$tag signUpUser")
                        }

                        else -> {
                            Toast.showWithLogger(
                                context.getString(R.string.error), "$tag signUpUser"
                            )
                        }
                    }
                }
            }
        }
    }

    fun signInUser(email: String, password: String) {
        viewModelScope.launch {
            val result = userRepository.loginUser(email, password)
            when (result) {
                is Resource.Success -> _authorizedUser.postValue(result.data)
                is Resource.Error -> Logger.logcat(result.message, "$tag updateUserFromUI")
                else -> Logger.logcat(context.getString(R.string.error), "$tag updateUserFromUI")
            }
        }
    }

    fun signInWithFacebook() {
        // TODO: impl signInWithFacebook
        Toast.showWithLogger(text = "Google sign in not implemented yet", tag = tag)
    }

    fun signInWithGoogle() {
        // TODO: impl signInWithGoogle
        Toast.showWithLogger(text = "Google sign in not implemented yet", tag = tag)
    }

    fun updateUserFromUI(screen: FragmentScreen, uiUser: User) {
        val authUser = _authorizedUser.value ?: User() // TODO: fix to nullable
        Logger.logcat("fun updateUserFromUI authUser: $authUser", tag)
        val tempUser = when (screen) {
            FragmentScreen.PROFILE -> {
                // TODO: fix this
                User(
                    uid = authUser.uid,
                    // Profile
                    profileStatus = uiUser.profileStatus,
                    position = uiUser.position,
                    category = uiUser.category,
                    skills = uiUser.skills,
                    experienceProgress = uiUser.experienceProgress,
                    salary = uiUser.salary,
                    country = uiUser.country,
                    online = uiUser.online,
                    leave = uiUser.leave,
                    relocation = uiUser.relocation,
                    city = uiUser.city,
                    cityMoving = uiUser.cityMoving,
                    englishLevel = uiUser.englishLevel,
                    experienceDescription = uiUser.experienceDescription,
                    achievements = uiUser.achievements,
                    expectation = uiUser.expectation,
                    expectationCombatant = uiUser.expectationCombatant,
                    employmentOptionsRemote = uiUser.employmentOptionsRemote,
                    employmentOptionsOffice = uiUser.employmentOptionsOffice,
                    employmentOptionsPartTime = uiUser.employmentOptionsPartTime,
                    employmentOptionsFreelance = uiUser.employmentOptionsFreelance,
                    hourlyRate = uiUser.hourlyRate,
                    notConsideringDomainsAdult = uiUser.notConsideringDomainsAdult,
                    notConsideringDomainsGambling = uiUser.notConsideringDomainsGambling,
                    notConsideringDomainsDating = uiUser.notConsideringDomainsDating,
                    notConsideringDomainsGameDev = uiUser.notConsideringDomainsGameDev,
                    notConsideringDomainsCrypto = uiUser.notConsideringDomainsCrypto,
                    notConsideringTypeCompanyAgency = uiUser.notConsideringTypeCompanyAgency,
                    notConsideringTypeCompanyOutsource = uiUser.notConsideringTypeCompanyOutsource,
                    notConsideringTypeCompanyOutStaff = uiUser.notConsideringTypeCompanyOutStaff,
                    notConsideringTypeCompanyProduct = uiUser.notConsideringTypeCompanyProduct,
                    notConsideringTypeCompanyStartUp = uiUser.notConsideringTypeCompanyStartUp,
                    questionForEmployer = uiUser.questionForEmployer,
                    preferredLanguageUkrainian = uiUser.preferredLanguageUkrainian,
                    preferredLanguageEnglish = uiUser.preferredLanguageEnglish,
                    preferredCommunication = uiUser.preferredCommunication,
                    // Contacts and CV
                    fullName = authUser.fullName,
                    email = authUser.email,
                    skype = authUser.skype,
                    phone = authUser.phone,
                    telegram = authUser.telegram,
                    whatsApp = authUser.whatsApp,
                    linkedIn = authUser.linkedIn,
                    gitHub = authUser.gitHub,
                    portfolio = authUser.portfolio,
                    cv = authUser.cv,
                    // Subscriptions
                    accordingVacancies = authUser.accordingVacancies,
                    notificationsFromEmployers = authUser.notificationsFromEmployers,
                    automaticOffers = authUser.automaticOffers,
                    // Stop list
                    search = authUser.search,
                    blockedRecruiters = authUser.blockedRecruiters,
                    hiddenVacancies = authUser.hiddenVacancies
                )
            }

            FragmentScreen.CONTACTS -> {
                User(
                    uid = authUser.uid,
                    // Profile
                    profileStatus = authUser.profileStatus,
                    position = authUser.position,
                    category = authUser.category,
                    skills = authUser.skills,
                    experienceProgress = authUser.experienceProgress,
                    salary = authUser.salary,
                    country = authUser.country,
                    online = authUser.online,
                    leave = authUser.leave,
                    relocation = authUser.relocation,
                    city = authUser.city,
                    cityMoving = authUser.cityMoving,
                    englishLevel = authUser.englishLevel,
                    experienceDescription = authUser.experienceDescription,
                    achievements = authUser.achievements,
                    expectation = authUser.expectation,
                    expectationCombatant = authUser.expectationCombatant,
                    employmentOptionsRemote = authUser.employmentOptionsRemote,
                    employmentOptionsOffice = authUser.employmentOptionsOffice,
                    employmentOptionsPartTime = authUser.employmentOptionsPartTime,
                    employmentOptionsFreelance = authUser.employmentOptionsFreelance,
                    hourlyRate = authUser.hourlyRate,
                    notConsideringDomainsAdult = authUser.notConsideringDomainsAdult,
                    notConsideringDomainsGambling = authUser.notConsideringDomainsGambling,
                    notConsideringDomainsDating = authUser.notConsideringDomainsDating,
                    notConsideringDomainsGameDev = authUser.notConsideringDomainsGameDev,
                    notConsideringDomainsCrypto = authUser.notConsideringDomainsCrypto,
                    notConsideringTypeCompanyAgency = authUser.notConsideringTypeCompanyAgency,
                    notConsideringTypeCompanyOutsource = authUser.notConsideringTypeCompanyOutsource,
                    notConsideringTypeCompanyOutStaff = authUser.notConsideringTypeCompanyOutStaff,
                    notConsideringTypeCompanyProduct = authUser.notConsideringTypeCompanyProduct,
                    notConsideringTypeCompanyStartUp = authUser.notConsideringTypeCompanyStartUp,
                    questionForEmployer = authUser.questionForEmployer,
                    preferredLanguageUkrainian = authUser.preferredLanguageUkrainian,
                    preferredLanguageEnglish = authUser.preferredLanguageEnglish,
                    preferredCommunication = authUser.preferredCommunication,
                    // Contacts and CV
                    fullName = uiUser.fullName,
                    email = uiUser.email,
                    skype = uiUser.skype,
                    phone = uiUser.phone,
                    telegram = uiUser.telegram,
                    whatsApp = uiUser.whatsApp,
                    linkedIn = uiUser.linkedIn,
                    gitHub = uiUser.gitHub,
                    portfolio = uiUser.portfolio,
                    cv = uiUser.cv,
                    // Subscriptions
                    accordingVacancies = authUser.accordingVacancies,
                    notificationsFromEmployers = authUser.notificationsFromEmployers,
                    automaticOffers = authUser.automaticOffers,
                    // Stop list
                    search = authUser.search,
                    blockedRecruiters = authUser.blockedRecruiters,
                    hiddenVacancies = authUser.hiddenVacancies
                )
            }

            FragmentScreen.SUBSCRIPTIONS -> {
                User(
                    uid = authUser.uid,
                    // Profile
                    profileStatus = authUser.profileStatus,
                    position = authUser.position,
                    category = authUser.category,
                    skills = authUser.skills,
                    experienceProgress = authUser.experienceProgress,
                    salary = authUser.salary,
                    country = authUser.country,
                    online = authUser.online,
                    leave = authUser.leave,
                    relocation = authUser.relocation,
                    city = authUser.city,
                    cityMoving = authUser.cityMoving,
                    englishLevel = authUser.englishLevel,
                    experienceDescription = authUser.experienceDescription,
                    achievements = authUser.achievements,
                    expectation = authUser.expectation,
                    expectationCombatant = authUser.expectationCombatant,
                    employmentOptionsRemote = authUser.employmentOptionsRemote,
                    employmentOptionsOffice = authUser.employmentOptionsOffice,
                    employmentOptionsPartTime = authUser.employmentOptionsPartTime,
                    employmentOptionsFreelance = authUser.employmentOptionsFreelance,
                    hourlyRate = authUser.hourlyRate,
                    notConsideringDomainsAdult = authUser.notConsideringDomainsAdult,
                    notConsideringDomainsGambling = authUser.notConsideringDomainsGambling,
                    notConsideringDomainsDating = authUser.notConsideringDomainsDating,
                    notConsideringDomainsGameDev = authUser.notConsideringDomainsGameDev,
                    notConsideringDomainsCrypto = authUser.notConsideringDomainsCrypto,
                    notConsideringTypeCompanyAgency = authUser.notConsideringTypeCompanyAgency,
                    notConsideringTypeCompanyOutsource = authUser.notConsideringTypeCompanyOutsource,
                    notConsideringTypeCompanyOutStaff = authUser.notConsideringTypeCompanyOutStaff,
                    notConsideringTypeCompanyProduct = authUser.notConsideringTypeCompanyProduct,
                    notConsideringTypeCompanyStartUp = authUser.notConsideringTypeCompanyStartUp,
                    questionForEmployer = authUser.questionForEmployer,
                    preferredLanguageUkrainian = authUser.preferredLanguageUkrainian,
                    preferredLanguageEnglish = authUser.preferredLanguageEnglish,
                    preferredCommunication = authUser.preferredCommunication,
                    // Contacts and CV
                    fullName = authUser.fullName,
                    email = authUser.email,
                    skype = authUser.skype,
                    phone = authUser.phone,
                    telegram = authUser.telegram,
                    whatsApp = authUser.whatsApp,
                    linkedIn = authUser.linkedIn,
                    gitHub = authUser.gitHub,
                    portfolio = authUser.portfolio,
                    cv = authUser.cv,
                    // Subscriptions
                    accordingVacancies = uiUser.accordingVacancies,
                    notificationsFromEmployers = uiUser.notificationsFromEmployers,
                    automaticOffers = uiUser.automaticOffers,
                    // Stop list
                    search = authUser.search,
                    blockedRecruiters = authUser.blockedRecruiters,
                    hiddenVacancies = authUser.hiddenVacancies
                )
            }

            FragmentScreen.STOP_LIST -> {
                User(
                    uid = authUser.uid,
                    // Profile
                    profileStatus = authUser.profileStatus,
                    position = authUser.position,
                    category = authUser.category,
                    skills = authUser.skills,
                    experienceProgress = authUser.experienceProgress,
                    salary = authUser.salary,
                    country = authUser.country,
                    online = authUser.online,
                    leave = authUser.leave,
                    relocation = authUser.relocation,
                    city = authUser.city,
                    cityMoving = authUser.cityMoving,
                    englishLevel = authUser.englishLevel,
                    experienceDescription = authUser.experienceDescription,
                    achievements = authUser.achievements,
                    expectation = authUser.expectation,
                    expectationCombatant = authUser.expectationCombatant,
                    employmentOptionsRemote = authUser.employmentOptionsRemote,
                    employmentOptionsOffice = authUser.employmentOptionsOffice,
                    employmentOptionsPartTime = authUser.employmentOptionsPartTime,
                    employmentOptionsFreelance = authUser.employmentOptionsFreelance,
                    hourlyRate = authUser.hourlyRate,
                    notConsideringDomainsAdult = authUser.notConsideringDomainsAdult,
                    notConsideringDomainsGambling = authUser.notConsideringDomainsGambling,
                    notConsideringDomainsDating = authUser.notConsideringDomainsDating,
                    notConsideringDomainsGameDev = authUser.notConsideringDomainsGameDev,
                    notConsideringDomainsCrypto = authUser.notConsideringDomainsCrypto,
                    notConsideringTypeCompanyAgency = authUser.notConsideringTypeCompanyAgency,
                    notConsideringTypeCompanyOutsource = authUser.notConsideringTypeCompanyOutsource,
                    notConsideringTypeCompanyOutStaff = authUser.notConsideringTypeCompanyOutStaff,
                    notConsideringTypeCompanyProduct = authUser.notConsideringTypeCompanyProduct,
                    notConsideringTypeCompanyStartUp = authUser.notConsideringTypeCompanyStartUp,
                    questionForEmployer = authUser.questionForEmployer,
                    preferredLanguageUkrainian = authUser.preferredLanguageUkrainian,
                    preferredLanguageEnglish = authUser.preferredLanguageEnglish,
                    preferredCommunication = authUser.preferredCommunication,
                    // Contacts and CV
                    fullName = authUser.fullName,
                    email = authUser.email,
                    skype = authUser.skype,
                    phone = authUser.phone,
                    telegram = authUser.telegram,
                    whatsApp = authUser.whatsApp,
                    linkedIn = authUser.linkedIn,
                    gitHub = authUser.gitHub,
                    portfolio = authUser.portfolio,
                    cv = authUser.cv,
                    // Subscriptions
                    accordingVacancies = authUser.accordingVacancies,
                    notificationsFromEmployers = authUser.notificationsFromEmployers,
                    automaticOffers = authUser.automaticOffers,
                    // Stop list
                    search = uiUser.search,
                    blockedRecruiters = uiUser.blockedRecruiters,
                    hiddenVacancies = uiUser.hiddenVacancies
                )
            }
        }
        Logger.logcat("fun updateUserFromUI tempUser: $tempUser", tag)
        viewModelScope.launch {
            val result = userRepository.updateUserToDatabase(tempUser)
            when (result) {
                is Resource.Success -> _authorizedUser.postValue(result.data)
                is Resource.Error -> Logger.logcat(result.message, "$tag updateUserFromUI")
                else -> Logger.logcat(context.getString(R.string.error), "$tag updateUserFromUI")
            }
        }
    }

    fun navigateToDashboard(navControllerProvider: NavControllerProvider) {
        val navController = navControllerProvider.getNavController()
        navController.navigate(R.id.nav_dashboard_web_view) // TODO: change to action nav_dashboard
    }

    fun navigateToProfile(navControllerProvider: NavControllerProvider) {
        val navController = navControllerProvider.getNavController()
        navController.navigate(R.id.nav_profile_pager_fragment) // TODO: change to action nav_dashboard
    }

    fun signOut() {
        userRepository.signOut()
        _authorizedUser.postValue(null)
    }
}