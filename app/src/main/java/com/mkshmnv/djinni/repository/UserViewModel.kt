package com.mkshmnv.djinni.repository

import android.app.Application
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.Toast
import com.mkshmnv.djinni.isEmail
import com.mkshmnv.djinni.model.User
import com.mkshmnv.djinni.ui.account.FragmentScreen
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    // For logger
    private val tag = this::class.simpleName!!

    // Get repository
    private val userRepository = UserRepository()

    // LiveData
    private val _authorizedUser = MutableLiveData<User?>()
    val authorizedUser: LiveData<User?> = _authorizedUser

    fun signUpUser(
        etEmail: AppCompatEditText,
        etPass: AppCompatEditText,
        etConfPass: AppCompatEditText,
        onComplete: () -> Unit
    ) {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confPass = etConfPass.text.toString()
        when {
            email.isEmpty() -> etEmail.error = R.string.auth_field_email_is_empty.getRes()

            email.isEmail() -> etEmail.error = R.string.auth_email_is_not_valid.getRes()

            pass.isEmpty() -> etPass.error = R.string.auth_field_password_is_empty.getRes()

            pass.length < 6 -> etPass.error = R.string.auth_password_is_too_short.getRes()

            confPass.isEmpty() -> etConfPass.error =
                R.string.auth_field_confirm_password_is_empty.getRes()

            confPass.length < 6 -> etConfPass.error = R.string.auth_password_is_too_short.getRes()

            pass != confPass -> {
                etPass.error = R.string.auth_passwords_do_not_match.getRes()
                etConfPass.error = R.string.auth_passwords_do_not_match.getRes()
            }

            else -> {
                viewModelScope.launch {
                    val result = userRepository.registrationUser(email, pass)
                    when (result) {
                        is Resource.Success -> {
                            Toast.showWithLogger(
                                R.string.auth_registration_success.getRes(),
                                "$tag signUpUser"
                            )
                            onComplete.invoke()
                        }

                        is Resource.Error -> Toast.showWithLogger(result.message, "$tag signUpUser")

                        else -> Toast.showWithLogger(R.string.error.getRes(), "$tag signUpUser")
                    }
                }
            }
        }
    }

    fun signInUser(email: String, password: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            val result = userRepository.loginUser(email, password)
            when (result) {
                is Resource.Success -> {
                    val user = result.data
                    _authorizedUser.postValue(user)
                    onComplete.invoke()
                }

                is Resource.Error -> Logger.logcat(result.message, "$tag updateUserFromUI")
                else -> Logger.logcat(R.string.error.getRes(), "$tag updateUserFromUI")
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
        Logger.logcat("updateUserFromUI screen - $screen, uiUser - $uiUser", tag)
        val currentAuthUser = _authorizedUser.value
            ?: throw NullPointerException("AuthorizedUser is null")
        val tempUser = when (screen) {
            FragmentScreen.PROFILE -> {
                // TODO: fix this
                User(
                    uid = currentAuthUser.uid,
                    // Profile
                    profileStatus = uiUser.profileStatus,
                    profilePosition = uiUser.profilePosition,
                    profileCategory = uiUser.profileCategory,
                    profileSkills = uiUser.profileSkills,
                    profileExpProgress = uiUser.profileExpProgress,
                    profileSalary = uiUser.profileSalary,
                    profileCountry = uiUser.profileCountry,
                    profileOnline = uiUser.profileOnline,
                    profileLeave = uiUser.profileLeave,
                    profileRelocation = uiUser.profileRelocation,
                    profileCity = uiUser.profileCity,
                    profileCityMoving = uiUser.profileCityMoving,
                    profileEngLevel = uiUser.profileEngLevel,
                    profileExpDescription = uiUser.profileExpDescription,
                    profileAchievements = uiUser.profileAchievements,
                    profileExpectation = uiUser.profileExpectation,
                    profileCombatant = uiUser.profileCombatant,
                    profileOptionRemote = uiUser.profileOptionRemote,
                    profileOptionOffice = uiUser.profileOptionOffice,
                    profileOptionPartTime = uiUser.profileOptionPartTime,
                    profileOptionFreelance = uiUser.profileOptionFreelance,
                    profileHourlyRate = uiUser.profileHourlyRate,
                    profileNotAdult = uiUser.profileNotAdult,
                    profileNotGambling = uiUser.profileNotGambling,
                    profileNotDating = uiUser.profileNotDating,
                    profileNotGameDev = uiUser.profileNotGameDev,
                    profileNotCrypto = uiUser.profileNotCrypto,
                    profileNotAgency = uiUser.profileNotAgency,
                    profileNotOutsource = uiUser.profileNotOutsource,
                    profileNotOutStaff = uiUser.profileNotOutStaff,
                    profileNotProduct = uiUser.profileNotProduct,
                    profileNotStartUp = uiUser.profileNotStartUp,
                    profileQuesToEmployer = uiUser.profileQuesToEmployer,
                    profilePrefUkrainian = uiUser.profilePrefUkrainian,
                    profilePrefEnglish = uiUser.profilePrefEnglish,
                    profilePrefComm = uiUser.profilePrefComm,
                    // Contacts and CV
                    contactsFullName = currentAuthUser.contactsFullName,
                    contactsEmail = currentAuthUser.contactsEmail,
                    contactsSkype = currentAuthUser.contactsSkype,
                    contactsPhone = currentAuthUser.contactsPhone,
                    contactsTelegram = currentAuthUser.contactsTelegram,
                    contactsWhatsApp = currentAuthUser.contactsWhatsApp,
                    contactsLinkedIn = currentAuthUser.contactsLinkedIn,
                    contactsGitHub = currentAuthUser.contactsGitHub,
                    contactsPortfolio = currentAuthUser.contactsPortfolio,
                    contactsCV = currentAuthUser.contactsCV,
                    // Subscriptions
                    subsVacancies = currentAuthUser.subsVacancies,
                    subsNotifications = currentAuthUser.subsNotifications,
                    subsAutoOffers = currentAuthUser.subsAutoOffers,
                    // Stop list
                    stopListSearch = currentAuthUser.stopListSearch,
                    stopListBlockCompanies = currentAuthUser.stopListBlockCompanies,
                    stopListBlockRecruiters = currentAuthUser.stopListBlockRecruiters,
                    stopListBlockVacancies = currentAuthUser.stopListBlockVacancies
                )
            }

            FragmentScreen.CONTACTS -> {
                User(
                    uid = currentAuthUser.uid,
                    // Profile
                    profileStatus = currentAuthUser.profileStatus,
                    profilePosition = currentAuthUser.profilePosition,
                    profileCategory = currentAuthUser.profileCategory,
                    profileSkills = currentAuthUser.profileSkills,
                    profileExpProgress = currentAuthUser.profileExpProgress,
                    profileSalary = currentAuthUser.profileSalary,
                    profileCountry = currentAuthUser.profileCountry,
                    profileOnline = currentAuthUser.profileOnline,
                    profileLeave = currentAuthUser.profileLeave,
                    profileRelocation = currentAuthUser.profileRelocation,
                    profileCity = currentAuthUser.profileCity,
                    profileCityMoving = currentAuthUser.profileCityMoving,
                    profileEngLevel = currentAuthUser.profileEngLevel,
                    profileExpDescription = currentAuthUser.profileExpDescription,
                    profileAchievements = currentAuthUser.profileAchievements,
                    profileExpectation = currentAuthUser.profileExpectation,
                    profileCombatant = currentAuthUser.profileCombatant,
                    profileOptionRemote = currentAuthUser.profileOptionRemote,
                    profileOptionOffice = currentAuthUser.profileOptionOffice,
                    profileOptionPartTime = currentAuthUser.profileOptionPartTime,
                    profileOptionFreelance = currentAuthUser.profileOptionFreelance,
                    profileHourlyRate = currentAuthUser.profileHourlyRate,
                    profileNotAdult = currentAuthUser.profileNotAdult,
                    profileNotGambling = currentAuthUser.profileNotGambling,
                    profileNotDating = currentAuthUser.profileNotDating,
                    profileNotGameDev = currentAuthUser.profileNotGameDev,
                    profileNotCrypto = currentAuthUser.profileNotCrypto,
                    profileNotAgency = currentAuthUser.profileNotAgency,
                    profileNotOutsource = currentAuthUser.profileNotOutsource,
                    profileNotOutStaff = currentAuthUser.profileNotOutStaff,
                    profileNotProduct = currentAuthUser.profileNotProduct,
                    profileNotStartUp = currentAuthUser.profileNotStartUp,
                    profileQuesToEmployer = currentAuthUser.profileQuesToEmployer,
                    profilePrefUkrainian = currentAuthUser.profilePrefUkrainian,
                    profilePrefEnglish = currentAuthUser.profilePrefEnglish,
                    profilePrefComm = currentAuthUser.profilePrefComm,
                    // Contacts and CV
                    contactsFullName = uiUser.contactsFullName,
                    contactsEmail = uiUser.contactsEmail,
                    contactsSkype = uiUser.contactsSkype,
                    contactsPhone = uiUser.contactsPhone,
                    contactsTelegram = uiUser.contactsTelegram,
                    contactsWhatsApp = uiUser.contactsWhatsApp,
                    contactsLinkedIn = uiUser.contactsLinkedIn,
                    contactsGitHub = uiUser.contactsGitHub,
                    contactsPortfolio = uiUser.contactsPortfolio,
                    contactsCV = uiUser.contactsCV,
                    // Subscriptions
                    subsVacancies = currentAuthUser.subsVacancies,
                    subsNotifications = currentAuthUser.subsNotifications,
                    subsAutoOffers = currentAuthUser.subsAutoOffers,
                    // Stop list
                    stopListSearch = currentAuthUser.stopListSearch,
                    stopListBlockCompanies = currentAuthUser.stopListBlockCompanies,
                    stopListBlockRecruiters = currentAuthUser.stopListBlockRecruiters,
                    stopListBlockVacancies = currentAuthUser.stopListBlockVacancies
                )
            }

            FragmentScreen.SUBS -> {
                User(
                    uid = currentAuthUser.uid,
                    // Profile
                    profileStatus = currentAuthUser.profileStatus,
                    profilePosition = currentAuthUser.profilePosition,
                    profileCategory = currentAuthUser.profileCategory,
                    profileSkills = currentAuthUser.profileSkills,
                    profileExpProgress = currentAuthUser.profileExpProgress,
                    profileSalary = currentAuthUser.profileSalary,
                    profileCountry = currentAuthUser.profileCountry,
                    profileOnline = currentAuthUser.profileOnline,
                    profileLeave = currentAuthUser.profileLeave,
                    profileRelocation = currentAuthUser.profileRelocation,
                    profileCity = currentAuthUser.profileCity,
                    profileCityMoving = currentAuthUser.profileCityMoving,
                    profileEngLevel = currentAuthUser.profileEngLevel,
                    profileExpDescription = currentAuthUser.profileExpDescription,
                    profileAchievements = currentAuthUser.profileAchievements,
                    profileExpectation = currentAuthUser.profileExpectation,
                    profileCombatant = currentAuthUser.profileCombatant,
                    profileOptionRemote = currentAuthUser.profileOptionRemote,
                    profileOptionOffice = currentAuthUser.profileOptionOffice,
                    profileOptionPartTime = currentAuthUser.profileOptionPartTime,
                    profileOptionFreelance = currentAuthUser.profileOptionFreelance,
                    profileHourlyRate = currentAuthUser.profileHourlyRate,
                    profileNotAdult = currentAuthUser.profileNotAdult,
                    profileNotGambling = currentAuthUser.profileNotGambling,
                    profileNotDating = currentAuthUser.profileNotDating,
                    profileNotGameDev = currentAuthUser.profileNotGameDev,
                    profileNotCrypto = currentAuthUser.profileNotCrypto,
                    profileNotAgency = currentAuthUser.profileNotAgency,
                    profileNotOutsource = currentAuthUser.profileNotOutsource,
                    profileNotOutStaff = currentAuthUser.profileNotOutStaff,
                    profileNotProduct = currentAuthUser.profileNotProduct,
                    profileNotStartUp = currentAuthUser.profileNotStartUp,
                    profileQuesToEmployer = currentAuthUser.profileQuesToEmployer,
                    profilePrefUkrainian = currentAuthUser.profilePrefUkrainian,
                    profilePrefEnglish = currentAuthUser.profilePrefEnglish,
                    profilePrefComm = currentAuthUser.profilePrefComm,
                    // Contacts and CV
                    contactsFullName = currentAuthUser.contactsFullName,
                    contactsEmail = currentAuthUser.contactsEmail,
                    contactsSkype = currentAuthUser.contactsSkype,
                    contactsPhone = currentAuthUser.contactsPhone,
                    contactsTelegram = currentAuthUser.contactsTelegram,
                    contactsWhatsApp = currentAuthUser.contactsWhatsApp,
                    contactsLinkedIn = currentAuthUser.contactsLinkedIn,
                    contactsGitHub = currentAuthUser.contactsGitHub,
                    contactsPortfolio = currentAuthUser.contactsPortfolio,
                    contactsCV = currentAuthUser.contactsCV,
                    // Subscriptions
                    subsVacancies = uiUser.subsVacancies,
                    subsNotifications = uiUser.subsNotifications,
                    subsAutoOffers = uiUser.subsAutoOffers,
                    // Stop list
                    stopListSearch = currentAuthUser.stopListSearch,
                    stopListBlockCompanies = currentAuthUser.stopListBlockCompanies,
                    stopListBlockRecruiters = currentAuthUser.stopListBlockRecruiters,
                    stopListBlockVacancies = currentAuthUser.stopListBlockVacancies
                )
            }

            FragmentScreen.STOP_LIST -> {
                User(
                    uid = currentAuthUser.uid,
                    // Profile
                    profileStatus = currentAuthUser.profileStatus,
                    profilePosition = currentAuthUser.profilePosition,
                    profileCategory = currentAuthUser.profileCategory,
                    profileSkills = currentAuthUser.profileSkills,
                    profileExpProgress = currentAuthUser.profileExpProgress,
                    profileSalary = currentAuthUser.profileSalary,
                    profileCountry = currentAuthUser.profileCountry,
                    profileOnline = currentAuthUser.profileOnline,
                    profileLeave = currentAuthUser.profileLeave,
                    profileRelocation = currentAuthUser.profileRelocation,
                    profileCity = currentAuthUser.profileCity,
                    profileCityMoving = currentAuthUser.profileCityMoving,
                    profileEngLevel = currentAuthUser.profileEngLevel,
                    profileExpDescription = currentAuthUser.profileExpDescription,
                    profileAchievements = currentAuthUser.profileAchievements,
                    profileExpectation = currentAuthUser.profileExpectation,
                    profileCombatant = currentAuthUser.profileCombatant,
                    profileOptionRemote = currentAuthUser.profileOptionRemote,
                    profileOptionOffice = currentAuthUser.profileOptionOffice,
                    profileOptionPartTime = currentAuthUser.profileOptionPartTime,
                    profileOptionFreelance = currentAuthUser.profileOptionFreelance,
                    profileHourlyRate = currentAuthUser.profileHourlyRate,
                    profileNotAdult = currentAuthUser.profileNotAdult,
                    profileNotGambling = currentAuthUser.profileNotGambling,
                    profileNotDating = currentAuthUser.profileNotDating,
                    profileNotGameDev = currentAuthUser.profileNotGameDev,
                    profileNotCrypto = currentAuthUser.profileNotCrypto,
                    profileNotAgency = currentAuthUser.profileNotAgency,
                    profileNotOutsource = currentAuthUser.profileNotOutsource,
                    profileNotOutStaff = currentAuthUser.profileNotOutStaff,
                    profileNotProduct = currentAuthUser.profileNotProduct,
                    profileNotStartUp = currentAuthUser.profileNotStartUp,
                    profileQuesToEmployer = currentAuthUser.profileQuesToEmployer,
                    profilePrefUkrainian = currentAuthUser.profilePrefUkrainian,
                    profilePrefEnglish = currentAuthUser.profilePrefEnglish,
                    profilePrefComm = currentAuthUser.profilePrefComm,
                    // Contacts and CV
                    contactsFullName = currentAuthUser.contactsFullName,
                    contactsEmail = currentAuthUser.contactsEmail,
                    contactsSkype = currentAuthUser.contactsSkype,
                    contactsPhone = currentAuthUser.contactsPhone,
                    contactsTelegram = currentAuthUser.contactsTelegram,
                    contactsWhatsApp = currentAuthUser.contactsWhatsApp,
                    contactsLinkedIn = currentAuthUser.contactsLinkedIn,
                    contactsGitHub = currentAuthUser.contactsGitHub,
                    contactsPortfolio = currentAuthUser.contactsPortfolio,
                    contactsCV = currentAuthUser.contactsCV,
                    // Subscriptions
                    subsVacancies = currentAuthUser.subsVacancies,
                    subsNotifications = currentAuthUser.subsNotifications,
                    subsAutoOffers = currentAuthUser.subsAutoOffers,
                    // Stop list
                    stopListSearch = uiUser.stopListSearch,
                    stopListBlockCompanies = uiUser.stopListBlockCompanies,
                    stopListBlockRecruiters = uiUser.stopListBlockRecruiters,
                    stopListBlockVacancies = uiUser.stopListBlockVacancies
                )
            }
        }
        Logger.logcat("updateUserFromUI tempUser - $tempUser", tag)
        viewModelScope.launch {
            val result = userRepository.updateUser(tempUser)
            when (result) {
                is Resource.Success -> {
                    val user = result.data
                    _authorizedUser.postValue(user)
                }

                is Resource.Error -> Logger.logcat(result.message, "$tag updateUserFromUI")
                else -> Logger.logcat(R.string.error.getRes(), "$tag updateUserFromUI")
            }
        }
    }

    fun signOutUser(onComplete: () -> Unit) {
        viewModelScope.launch {
            val currentAuthUser = _authorizedUser.value
                ?: throw NullPointerException("AuthorizedUser is null")
            val result = userRepository.signOut(currentAuthUser)
            when (result) {
                is Resource.Success -> {
                    Logger.logcat("User Sign Out Success", "$tag signOutUser")
                    onComplete.invoke()
                }

                is Resource.Error -> Logger.logcat(result.message, "$tag signOutUser")
                else -> Logger.logcat(R.string.error.getRes(), "$tag signOutUser")
            }
        }
    }

    fun deleteUser(onComplete: () -> Unit) {
        val currentAuthUser = _authorizedUser.value
            ?: throw NullPointerException("AuthorizedUser is null")
        viewModelScope.launch {
            Logger.logcat("deleteUser currentAuthUser - $currentAuthUser", "$tag deleteUser")
            val result = userRepository.deleteUser(currentAuthUser)
            when (result) {
                is Resource.Success -> {
                    Logger.logcat("User deleted Success", "$tag deleteUser")
                    _authorizedUser.postValue(null)
                    onComplete.invoke()
                }

                is Resource.Error -> Logger.logcat(result.message, "$tag deleteUser")
                else -> Logger.logcat(R.string.error.getRes(), "$tag deleteUser")
            }
        }
    }

    // Extension function for get string from resources
    private fun Int.getRes() = getApplication<Application>().getString(this)
}