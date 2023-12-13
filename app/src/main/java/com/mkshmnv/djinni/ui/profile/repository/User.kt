package com.mkshmnv.djinni.ui.profile.repository

class User(
    var uid: String = "",
    // Profile
    var profileStatus: Int = 0, // TODO: impl enum ProfileStatus
    var position: String = "",
    var category: String = "",
    var skills: String = "",
    var experienceProgress: Int = 0,
    var salary: Int = 0,
    var country: String = "",
    var online: Boolean = false,
    var leave: Boolean = false,
    var relocation: Boolean = false,
    var city: String = "",
    var cityMoving: Boolean = false,
    var englishLevel: Int = 0,
    var experienceDescription: String = "",
    var achievements: String = "",
    var expectation: String = "",
    var expectationCombatant: Boolean = false,
    var employmentOptionsRemote: Boolean = false,
    var employmentOptionsOffice: Boolean = false,
    var employmentOptionsPartTime: Boolean = false,
    var employmentOptionsFreelance: Boolean = false,
    var hourlyRate: Int = 0,
    var notConsideringDomainsAdult: Boolean = false,
    var notConsideringDomainsGambling: Boolean = false,
    var notConsideringDomainsDating: Boolean = false,
    var notConsideringDomainsGameDev: Boolean = false,
    var notConsideringDomainsCrypto: Boolean = false,
    var notConsideringTypeCompanyAgency: Boolean = false,
    var notConsideringTypeCompanyOutsource: Boolean = false,
    var notConsideringTypeCompanyOutStaff: Boolean = false,
    var notConsideringTypeCompanyProduct: Boolean = false,
    var notConsideringTypeCompanyStartUp: Boolean = false,
    var questionForEmployer: String = "",
    var preferredLanguageUkrainian: Boolean = false,
    var preferredLanguageEnglish: Boolean = false,
    var preferredCommunication: String = "", // TODO: impl enum/map PreferredCommunication
    // Contacts and CV
    var fullName: String = "",
    var email: String = "",
    var skype: String = "",
    var phone: String = "",
    var telegram: String = "",
    var whatsApp: String = "",
    var linkedIn: String = "",
    var gitHub: String = "",
    var portfolio: String = "",
    var cv: String = "",
    // Subscriptions
    var accordingVacancies: String = "",
    var notificationsFromEmployers: String = "",
    var automaticOffers: String = "",
    // Stop list
    var search: String = "",
    var blockedRecruiters: String = "",
    var hiddenVacancies: String = ""
    // Hires
    // TODO: impl Hires
) {
    fun updateUserFromUI(uiUser: User, from: String) {
        when(from) {
            "profile" -> {
                this.profileStatus = uiUser.profileStatus
                this.position = uiUser.position
                this.category = uiUser.category
                this.skills = uiUser.skills
                this.experienceProgress = uiUser.experienceProgress
                this.salary = uiUser.salary
                this.country = uiUser.country
                this.online = uiUser.online
                this.leave = uiUser.leave
                this.relocation = uiUser.relocation
                this.city = uiUser.city
                this.cityMoving = uiUser.cityMoving
                this.englishLevel = uiUser.englishLevel
                this.experienceDescription = uiUser.experienceDescription
                this.achievements = uiUser.achievements
                this.expectation = uiUser.expectation
                this.expectationCombatant = uiUser.expectationCombatant
                this.employmentOptionsRemote = uiUser.employmentOptionsRemote
                this.employmentOptionsOffice = uiUser.employmentOptionsOffice
                this.employmentOptionsPartTime = uiUser.employmentOptionsPartTime
                this.employmentOptionsFreelance = uiUser.employmentOptionsFreelance
                this.hourlyRate = uiUser.hourlyRate
                this.notConsideringDomainsAdult = uiUser.notConsideringDomainsAdult
                this.notConsideringDomainsGambling = uiUser.notConsideringDomainsGambling
                this.notConsideringDomainsDating = uiUser.notConsideringDomainsDating
                this.notConsideringDomainsGameDev = uiUser.notConsideringDomainsGameDev
                this.notConsideringDomainsCrypto = uiUser.notConsideringDomainsCrypto
                this.notConsideringTypeCompanyAgency = uiUser.notConsideringTypeCompanyAgency
                this.notConsideringTypeCompanyOutsource = uiUser.notConsideringTypeCompanyOutsource
                this.notConsideringTypeCompanyOutStaff = uiUser.notConsideringTypeCompanyOutStaff
                this.notConsideringTypeCompanyProduct = uiUser.notConsideringTypeCompanyProduct
                this.notConsideringTypeCompanyStartUp = uiUser.notConsideringTypeCompanyStartUp
                this.questionForEmployer = uiUser.questionForEmployer
                this.preferredLanguageUkrainian = uiUser.preferredLanguageUkrainian
                this.preferredLanguageEnglish = uiUser.preferredLanguageEnglish
                this.preferredCommunication = uiUser.preferredCommunication
            }
            "contacts" -> {
                this.fullName = uiUser.fullName
                this.email = uiUser.email
                this.skype = uiUser.skype
                this.phone = uiUser.phone
                this.telegram = uiUser.telegram
                this.whatsApp = uiUser.whatsApp
                this.linkedIn = uiUser.linkedIn
                this.gitHub = uiUser.gitHub
                this.portfolio = uiUser.portfolio
                this.cv = uiUser.cv
            }
            "subscriptions" -> {
                this.accordingVacancies = uiUser.accordingVacancies
                this.notificationsFromEmployers = uiUser.notificationsFromEmployers
                this.automaticOffers = uiUser.automaticOffers
            }
            "stopList" -> {
                this.search = uiUser.search
                this.blockedRecruiters = uiUser.blockedRecruiters
                this.hiddenVacancies = uiUser.hiddenVacancies
            }
        }
    }
}


enum class ProfileStatus { // TODO: impl ProfileStatus
    ACTIVE,
    PASSIVE,
    NOT_LOOKED
}