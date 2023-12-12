package com.mkshmnv.djinni.ui.profile.repository

data class User(
    val uid: String = "",
    // Profile
    val profileStatus: String = "", // TODO: impl enum ProfileStatus
    val position: String = "",
    val category: String = "",
    val skills: String = "",
    val experience: String = "",
    val salaryExpectations: String = "",
    val country: String = "",
    val city: String = "",
    val englishLevel: String = "",
    val descriptionExperience: String = "",
    val achievement: String = "",
    val expectation: String = "",
    val employmentOptions: String = "",
    val hourlyRate: String = "",
    val notConsidering: String = "",
    val questionForEmployer: String = "",
    val preferredLanguage: String = "",
    val preferredCommunication: String = "",
    // Contacts and CV
    val fullName: String = "",
    val email: String = "",
    val skype: String = "",
    val phone: String = "",
    val telegram: String = "",
    val whatsApp: String = "",
    val linkedIn : String = "",
    val gitHub: String = "",
    val portfolio: String = "",
    val cv: String = "",
    // Subscriptions
    val accordingVacancies: String = "",
    val notificationsFromEmployers : String = "",
    val automaticOffers: String = "",
    // Stop list
    val search: String = "",
    val blockedRecruiters: String = "",
    val hiddenVacancies: String = ""
    // Hires
    // TODO: impl Hires
)



enum class ProfileStatus { // TODO: impl ProfileStatus
    ACTIVE,
    PASSIVE,
    NOT_LOOKED
}