package com.mkshmnv.djinni.ui.profile.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.Resource
import com.mkshmnv.djinni.Toast
import kotlinx.coroutines.tasks.await

class UserRepository {
    // For logger
    private val tag = "Repo"

    // Firebase
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference.child("users")

    suspend fun registrationUser(email: String, password: String): Resource<User> {
        return try {
            val auth = auth.createUserWithEmailAndPassword(email, password).await()
            val user = User(uid = auth.user?.uid.toString(), email = email)
            Logger.logcat("Registration user - $user ", "$tag registrationUser")
            database.child(user.uid).setValue(user)
            Resource.Success(user)
        } catch (e: Exception) {
            Toast.showWithLogger(e.message ?: "Registration failed", "registrationUser")
            Resource.Error(e.message ?: "Registration failed")
        }
    }

    suspend fun loginUser(email: String, password: String): User {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val userID = authResult.user?.uid.toString()
            val snapshot = database.child(userID).get().await()
            val userData = snapshot.getValue(User::class.java)
            Logger.logcat("Auth User Data  - $userData", "$tag loginUser")
            userData!!
        } catch (e: Exception) {
            Toast.showWithLogger(e.message ?: "Login failed", "loginUser")
            User(uid = "loginUser ERROR")
        }
    }

    suspend fun updateUserToDatabase(user: User): Resource<User> {
        return try {
//            updateUserFromUI(user, TypeUI.CONTACTS)
            database.child(user.uid).setValue(user).await()
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error getting user data")
        }
    }

    fun updateUserFromUI(authUser: User, uiUser: User, from: TypeUI) {
        when (from) {
            TypeUI.PROFILE -> {
                // TODO: fix this
//                User(
//                    profileStatus = uiUser.profileStatus,
//                )
                authUser.profileStatus = uiUser.profileStatus
                authUser.position = uiUser.position
                authUser.category = uiUser.category
                authUser.skills = uiUser.skills
                authUser.experienceProgress = uiUser.experienceProgress
                authUser.salary = uiUser.salary
                authUser.country = uiUser.country
                authUser.online = uiUser.online
                authUser.leave = uiUser.leave
                authUser.relocation = uiUser.relocation
                authUser.city = uiUser.city
                authUser.cityMoving = uiUser.cityMoving
                authUser.englishLevel = uiUser.englishLevel
                authUser.experienceDescription = uiUser.experienceDescription
                authUser.achievements = uiUser.achievements
                authUser.expectation = uiUser.expectation
                authUser.expectationCombatant = uiUser.expectationCombatant
                authUser.employmentOptionsRemote = uiUser.employmentOptionsRemote
                authUser.employmentOptionsOffice = uiUser.employmentOptionsOffice
                authUser.employmentOptionsPartTime = uiUser.employmentOptionsPartTime
                authUser.employmentOptionsFreelance = uiUser.employmentOptionsFreelance
                authUser.hourlyRate = uiUser.hourlyRate
                authUser.notConsideringDomainsAdult = uiUser.notConsideringDomainsAdult
                authUser.notConsideringDomainsGambling = uiUser.notConsideringDomainsGambling
                authUser.notConsideringDomainsDating = uiUser.notConsideringDomainsDating
                authUser.notConsideringDomainsGameDev = uiUser.notConsideringDomainsGameDev
                authUser.notConsideringDomainsCrypto = uiUser.notConsideringDomainsCrypto
                authUser.notConsideringTypeCompanyAgency = uiUser.notConsideringTypeCompanyAgency
                authUser.notConsideringTypeCompanyOutsource =
                    uiUser.notConsideringTypeCompanyOutsource
                authUser.notConsideringTypeCompanyOutStaff =
                    uiUser.notConsideringTypeCompanyOutStaff
                authUser.notConsideringTypeCompanyProduct = uiUser.notConsideringTypeCompanyProduct
                authUser.notConsideringTypeCompanyStartUp = uiUser.notConsideringTypeCompanyStartUp
                authUser.questionForEmployer = uiUser.questionForEmployer
                authUser.preferredLanguageUkrainian = uiUser.preferredLanguageUkrainian
                authUser.preferredLanguageEnglish = uiUser.preferredLanguageEnglish
                authUser.preferredCommunication = uiUser.preferredCommunication
            }

            TypeUI.CONTACTS -> {
                authUser.fullName = uiUser.fullName
                authUser.email = uiUser.email
                authUser.skype = uiUser.skype
                authUser.phone = uiUser.phone
                authUser.telegram = uiUser.telegram
                authUser.whatsApp = uiUser.whatsApp
                authUser.linkedIn = uiUser.linkedIn
                authUser.gitHub = uiUser.gitHub
                authUser.portfolio = uiUser.portfolio
                authUser.cv = uiUser.cv
            }

            TypeUI.SUBSCRIPTIONS -> {
                authUser.accordingVacancies = uiUser.accordingVacancies
                authUser.notificationsFromEmployers = uiUser.notificationsFromEmployers
                authUser.automaticOffers = uiUser.automaticOffers
            }

            TypeUI.STOP_LIST -> {
                authUser.search = uiUser.search
                authUser.blockedRecruiters = uiUser.blockedRecruiters
                authUser.hiddenVacancies = uiUser.hiddenVacancies
            }
        }
    }

    fun signOut() {
        auth.signOut()
    }
}