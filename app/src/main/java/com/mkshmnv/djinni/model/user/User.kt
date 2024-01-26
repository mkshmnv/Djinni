package com.mkshmnv.djinni.model.user

import com.google.gson.annotations.SerializedName

data class User(
    val uid: String = "",
    // Profile
    @SerializedName("profileStatus") val profileStatus: String = "2131296746", //ProfileStatus = ProfileStatus.NOT_LOOKED,
    val profilePosition: String = "",
    val profileCategory: String = "",
    val profileSkills: String = "",
    val profileExpProgress: Int = 0,
    val profileSalary: String = "",
    val profileCountry: String = "",
    val profileOnline: Boolean = false,
    val profileLeave: Boolean = false,
    val profileRelocation: Boolean = false,
    val profileCity: String = "",
    val profileCityMoving: Boolean = false,
    val profileEngLevel: String = "2131296745",
    val profileExpDescription: String = "",
    val profileAchievements: String = "",
    val profileExpectation: String = "",
    val profileCombatant: Boolean = false,
    val profileOptionRemote: Boolean = false,
    val profileOptionOffice: Boolean = false,
    val profileOptionPartTime: Boolean = false,
    val profileOptionFreelance: Boolean = false,
    val profileHourlyRate: String = "",
    val profileNotAdult: Boolean = false,
    val profileNotGambling: Boolean = false,
    val profileNotDating: Boolean = false,
    val profileNotGameDev: Boolean = false,
    val profileNotCrypto: Boolean = false,
    val profileNotAgency: Boolean = false,
    val profileNotOutsource: Boolean = false,
    val profileNotOutStaff: Boolean = false,
    val profileNotProduct: Boolean = false,
    val profileNotStartUp: Boolean = false,
    val profileQuesToEmployer: String = "",
    val profilePrefUkrainian: Boolean = false,
    val profilePrefEnglish: Boolean = false,
    val profilePrefComm: String = "", // TODO: impl enum/map PreferredCommunication
    // Contacts and CV
    val contactsFullName: String = "",
    val contactsEmail: String = "",
    val contactsSkype: String = "",
    val contactsPhone: String = "",
    val contactsTelegram: String = "",
    val contactsWhatsApp: String = "",
    val contactsLinkedIn: String = "",
    val contactsGitHub: String = "",
    val contactsPortfolio: String = "",
    val contactsCV: String = "",
    // Subscriptions
    var subsVacancies: String = "2131296752",
    val subsNotifications: String = "2131296749",
    val subsAutoOffers: Boolean = true,
    // Stop list
    val stopListSearch: String = "",
    val stopListBlockCompanies: String = "",
    val stopListBlockRecruiters: String = "",
    val stopListBlockVacancies: String = ""
    // Hires
    // TODO: impl Hires
)