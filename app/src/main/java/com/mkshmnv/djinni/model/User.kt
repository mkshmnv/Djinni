package com.mkshmnv.djinni.model

data class User(
    val uid: String = "",
    // Profile
    val profileStatus: String = "", //ProfileStatus = ProfileStatus.NOT_LOOKED,
    val position: String = "",
    val category: String = "",
    val skills: String = "",
    val experienceProgress: Int = 0,
    val salary: String = "",
    val country: String = "",
    val online: Boolean = false,
    val leave: Boolean = false,
    val relocation: Boolean = false,
    val city: String = "",
    val cityMoving: Boolean = false,
    val englishLevel: String = "",
    val experienceDescription: String = "",
    val achievements: String = "",
    val expectation: String = "",
    val expectationCombatant: Boolean = false,
    val employmentOptionsRemote: Boolean = false,
    val employmentOptionsOffice: Boolean = false,
    val employmentOptionsPartTime: Boolean = false,
    val employmentOptionsFreelance: Boolean = false,
    val hourlyRate: String = "",
    val notConsideringDomainsAdult: Boolean = false,
    val notConsideringDomainsGambling: Boolean = false,
    val notConsideringDomainsDating: Boolean = false,
    val notConsideringDomainsGameDev: Boolean = false,
    val notConsideringDomainsCrypto: Boolean = false,
    val notConsideringTypeCompanyAgency: Boolean = false,
    val notConsideringTypeCompanyOutsource: Boolean = false,
    val notConsideringTypeCompanyOutStaff: Boolean = false,
    val notConsideringTypeCompanyProduct: Boolean = false,
    val notConsideringTypeCompanyStartUp: Boolean = false,
    val questionForEmployer: String = "",
    val preferredLanguageUkrainian: Boolean = false,
    val preferredLanguageEnglish: Boolean = false,
    val preferredCommunication: String = "", // TODO: impl enum/map PreferredCommunication
    // Contacts and CV
    val fullName: String = "",
    val email: String = "",
    val skype: String = "",
    val phone: String = "",
    val telegram: String = "",
    val whatsApp: String = "",
    val linkedIn: String = "",
    val gitHub: String = "",
    val portfolio: String = "",
    val cv: String = "",
    // Subscriptions
    val accordingVacancies: String = "",
    val notificationsFromEmployers: String = "",
    val automaticOffers: String = "",
    // Stop list
    val search: String = "",
    val blockedRecruiters: String = "",
    val hiddenVacancies: String = ""
    // Hires
    // TODO: impl Hires
)