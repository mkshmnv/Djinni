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
        authorizedUser.observeForever {
            Logger.logcat("init authorizedUser - $it", tag)
        }
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
        val currentAuthUser = _authorizedUser.value ?: User() // TODO: fix to nullable
        val tempUser = when (screen) {
            FragmentScreen.PROFILE -> {
                // TODO: fix this
                User(
                    uid = currentAuthUser.uid,
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
                    fullName = currentAuthUser.fullName,
                    email = currentAuthUser.email,
                    skype = currentAuthUser.skype,
                    phone = currentAuthUser.phone,
                    telegram = currentAuthUser.telegram,
                    whatsApp = currentAuthUser.whatsApp,
                    linkedIn = currentAuthUser.linkedIn,
                    gitHub = currentAuthUser.gitHub,
                    portfolio = currentAuthUser.portfolio,
                    cv = currentAuthUser.cv,
                    // Subscriptions
                    accordingVacancies = currentAuthUser.accordingVacancies,
                    notificationsFromEmployers = currentAuthUser.notificationsFromEmployers,
                    automaticOffers = currentAuthUser.automaticOffers,
                    // Stop list
                    search = currentAuthUser.search,
                    blockedRecruiters = currentAuthUser.blockedRecruiters,
                    hiddenVacancies = currentAuthUser.hiddenVacancies
                )
            }

            FragmentScreen.CONTACTS -> {
                User(
                    uid = currentAuthUser.uid,
                    // Profile
                    profileStatus = currentAuthUser.profileStatus,
                    position = currentAuthUser.position,
                    category = currentAuthUser.category,
                    skills = currentAuthUser.skills,
                    experienceProgress = currentAuthUser.experienceProgress,
                    salary = currentAuthUser.salary,
                    country = currentAuthUser.country,
                    online = currentAuthUser.online,
                    leave = currentAuthUser.leave,
                    relocation = currentAuthUser.relocation,
                    city = currentAuthUser.city,
                    cityMoving = currentAuthUser.cityMoving,
                    englishLevel = currentAuthUser.englishLevel,
                    experienceDescription = currentAuthUser.experienceDescription,
                    achievements = currentAuthUser.achievements,
                    expectation = currentAuthUser.expectation,
                    expectationCombatant = currentAuthUser.expectationCombatant,
                    employmentOptionsRemote = currentAuthUser.employmentOptionsRemote,
                    employmentOptionsOffice = currentAuthUser.employmentOptionsOffice,
                    employmentOptionsPartTime = currentAuthUser.employmentOptionsPartTime,
                    employmentOptionsFreelance = currentAuthUser.employmentOptionsFreelance,
                    hourlyRate = currentAuthUser.hourlyRate,
                    notConsideringDomainsAdult = currentAuthUser.notConsideringDomainsAdult,
                    notConsideringDomainsGambling = currentAuthUser.notConsideringDomainsGambling,
                    notConsideringDomainsDating = currentAuthUser.notConsideringDomainsDating,
                    notConsideringDomainsGameDev = currentAuthUser.notConsideringDomainsGameDev,
                    notConsideringDomainsCrypto = currentAuthUser.notConsideringDomainsCrypto,
                    notConsideringTypeCompanyAgency = currentAuthUser.notConsideringTypeCompanyAgency,
                    notConsideringTypeCompanyOutsource = currentAuthUser.notConsideringTypeCompanyOutsource,
                    notConsideringTypeCompanyOutStaff = currentAuthUser.notConsideringTypeCompanyOutStaff,
                    notConsideringTypeCompanyProduct = currentAuthUser.notConsideringTypeCompanyProduct,
                    notConsideringTypeCompanyStartUp = currentAuthUser.notConsideringTypeCompanyStartUp,
                    questionForEmployer = currentAuthUser.questionForEmployer,
                    preferredLanguageUkrainian = currentAuthUser.preferredLanguageUkrainian,
                    preferredLanguageEnglish = currentAuthUser.preferredLanguageEnglish,
                    preferredCommunication = currentAuthUser.preferredCommunication,
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
                    accordingVacancies = currentAuthUser.accordingVacancies,
                    notificationsFromEmployers = currentAuthUser.notificationsFromEmployers,
                    automaticOffers = currentAuthUser.automaticOffers,
                    // Stop list
                    search = currentAuthUser.search,
                    blockedRecruiters = currentAuthUser.blockedRecruiters,
                    hiddenVacancies = currentAuthUser.hiddenVacancies
                )
            }

            FragmentScreen.SUBSCRIPTIONS -> {
                User(
                    uid = currentAuthUser.uid,
                    // Profile
                    profileStatus = currentAuthUser.profileStatus,
                    position = currentAuthUser.position,
                    category = currentAuthUser.category,
                    skills = currentAuthUser.skills,
                    experienceProgress = currentAuthUser.experienceProgress,
                    salary = currentAuthUser.salary,
                    country = currentAuthUser.country,
                    online = currentAuthUser.online,
                    leave = currentAuthUser.leave,
                    relocation = currentAuthUser.relocation,
                    city = currentAuthUser.city,
                    cityMoving = currentAuthUser.cityMoving,
                    englishLevel = currentAuthUser.englishLevel,
                    experienceDescription = currentAuthUser.experienceDescription,
                    achievements = currentAuthUser.achievements,
                    expectation = currentAuthUser.expectation,
                    expectationCombatant = currentAuthUser.expectationCombatant,
                    employmentOptionsRemote = currentAuthUser.employmentOptionsRemote,
                    employmentOptionsOffice = currentAuthUser.employmentOptionsOffice,
                    employmentOptionsPartTime = currentAuthUser.employmentOptionsPartTime,
                    employmentOptionsFreelance = currentAuthUser.employmentOptionsFreelance,
                    hourlyRate = currentAuthUser.hourlyRate,
                    notConsideringDomainsAdult = currentAuthUser.notConsideringDomainsAdult,
                    notConsideringDomainsGambling = currentAuthUser.notConsideringDomainsGambling,
                    notConsideringDomainsDating = currentAuthUser.notConsideringDomainsDating,
                    notConsideringDomainsGameDev = currentAuthUser.notConsideringDomainsGameDev,
                    notConsideringDomainsCrypto = currentAuthUser.notConsideringDomainsCrypto,
                    notConsideringTypeCompanyAgency = currentAuthUser.notConsideringTypeCompanyAgency,
                    notConsideringTypeCompanyOutsource = currentAuthUser.notConsideringTypeCompanyOutsource,
                    notConsideringTypeCompanyOutStaff = currentAuthUser.notConsideringTypeCompanyOutStaff,
                    notConsideringTypeCompanyProduct = currentAuthUser.notConsideringTypeCompanyProduct,
                    notConsideringTypeCompanyStartUp = currentAuthUser.notConsideringTypeCompanyStartUp,
                    questionForEmployer = currentAuthUser.questionForEmployer,
                    preferredLanguageUkrainian = currentAuthUser.preferredLanguageUkrainian,
                    preferredLanguageEnglish = currentAuthUser.preferredLanguageEnglish,
                    preferredCommunication = currentAuthUser.preferredCommunication,
                    // Contacts and CV
                    fullName = currentAuthUser.fullName,
                    email = currentAuthUser.email,
                    skype = currentAuthUser.skype,
                    phone = currentAuthUser.phone,
                    telegram = currentAuthUser.telegram,
                    whatsApp = currentAuthUser.whatsApp,
                    linkedIn = currentAuthUser.linkedIn,
                    gitHub = currentAuthUser.gitHub,
                    portfolio = currentAuthUser.portfolio,
                    cv = currentAuthUser.cv,
                    // Subscriptions
                    accordingVacancies = uiUser.accordingVacancies,
                    notificationsFromEmployers = uiUser.notificationsFromEmployers,
                    automaticOffers = uiUser.automaticOffers,
                    // Stop list
                    search = currentAuthUser.search,
                    blockedRecruiters = currentAuthUser.blockedRecruiters,
                    hiddenVacancies = currentAuthUser.hiddenVacancies
                )
            }

            FragmentScreen.STOP_LIST -> {
                User(
                    uid = currentAuthUser.uid,
                    // Profile
                    profileStatus = currentAuthUser.profileStatus,
                    position = currentAuthUser.position,
                    category = currentAuthUser.category,
                    skills = currentAuthUser.skills,
                    experienceProgress = currentAuthUser.experienceProgress,
                    salary = currentAuthUser.salary,
                    country = currentAuthUser.country,
                    online = currentAuthUser.online,
                    leave = currentAuthUser.leave,
                    relocation = currentAuthUser.relocation,
                    city = currentAuthUser.city,
                    cityMoving = currentAuthUser.cityMoving,
                    englishLevel = currentAuthUser.englishLevel,
                    experienceDescription = currentAuthUser.experienceDescription,
                    achievements = currentAuthUser.achievements,
                    expectation = currentAuthUser.expectation,
                    expectationCombatant = currentAuthUser.expectationCombatant,
                    employmentOptionsRemote = currentAuthUser.employmentOptionsRemote,
                    employmentOptionsOffice = currentAuthUser.employmentOptionsOffice,
                    employmentOptionsPartTime = currentAuthUser.employmentOptionsPartTime,
                    employmentOptionsFreelance = currentAuthUser.employmentOptionsFreelance,
                    hourlyRate = currentAuthUser.hourlyRate,
                    notConsideringDomainsAdult = currentAuthUser.notConsideringDomainsAdult,
                    notConsideringDomainsGambling = currentAuthUser.notConsideringDomainsGambling,
                    notConsideringDomainsDating = currentAuthUser.notConsideringDomainsDating,
                    notConsideringDomainsGameDev = currentAuthUser.notConsideringDomainsGameDev,
                    notConsideringDomainsCrypto = currentAuthUser.notConsideringDomainsCrypto,
                    notConsideringTypeCompanyAgency = currentAuthUser.notConsideringTypeCompanyAgency,
                    notConsideringTypeCompanyOutsource = currentAuthUser.notConsideringTypeCompanyOutsource,
                    notConsideringTypeCompanyOutStaff = currentAuthUser.notConsideringTypeCompanyOutStaff,
                    notConsideringTypeCompanyProduct = currentAuthUser.notConsideringTypeCompanyProduct,
                    notConsideringTypeCompanyStartUp = currentAuthUser.notConsideringTypeCompanyStartUp,
                    questionForEmployer = currentAuthUser.questionForEmployer,
                    preferredLanguageUkrainian = currentAuthUser.preferredLanguageUkrainian,
                    preferredLanguageEnglish = currentAuthUser.preferredLanguageEnglish,
                    preferredCommunication = currentAuthUser.preferredCommunication,
                    // Contacts and CV
                    fullName = currentAuthUser.fullName,
                    email = currentAuthUser.email,
                    skype = currentAuthUser.skype,
                    phone = currentAuthUser.phone,
                    telegram = currentAuthUser.telegram,
                    whatsApp = currentAuthUser.whatsApp,
                    linkedIn = currentAuthUser.linkedIn,
                    gitHub = currentAuthUser.gitHub,
                    portfolio = currentAuthUser.portfolio,
                    cv = currentAuthUser.cv,
                    // Subscriptions
                    accordingVacancies = currentAuthUser.accordingVacancies,
                    notificationsFromEmployers = currentAuthUser.notificationsFromEmployers,
                    automaticOffers = currentAuthUser.automaticOffers,
                    // Stop list
                    search = uiUser.search,
                    blockedRecruiters = uiUser.blockedRecruiters,
                    hiddenVacancies = uiUser.hiddenVacancies
                )
            }
        }
        viewModelScope.launch {
            val result = userRepository.updateUserToDatabase(tempUser)
            when (result) {
                is Resource.Success -> {
                    Logger.logcat("fun updateUserFromUI - upload user data to firebase: $tempUser", tag)
                    _authorizedUser.postValue(result.data)
                }
                is Resource.Error -> Logger.logcat(result.message, "$tag updateUserFromUI")
                else -> Logger.logcat(context.getString(R.string.error), "$tag updateUserFromUI")
            }
        }
    }

    fun signOut() {
        userRepository.signOut()
        _authorizedUser.postValue(null)
    }
}