package com.mkshmnv.djinni.model.employer

data class EmployerContact(
    val id: Int,
    val name: String,
    val photo: String,
    val position: String,
    val verifiedEmployer: Boolean,
    val successfulRecruitments: Boolean,
    val contactSince: String,
    val activity: String,
    val country: Country
)

data class Vacancy(
    val id: Int,
    val employer: Employer,
    val contact: EmployerContact,
    val title: String,
    val publishedDate: String,
    val views: Int,
    val reviews: Int,
    val description: String,
    val english: EnglishLevel,
    val salaryExpectations: Int,
    val category: Categories,
    val experience: ExperienceLevel,
    val domain: List<Domains>,
    val employmentType: List<EmploymentOption>,
    val location: Country,
    val candidateFrom: List<Country>
)

enum class EnglishLevel {
    NO_ENGLISH,
    ELEMENTARY,
    PRE_INTERMEDIATE,
    INTERMEDIATE,
    UPPER_INTERMEDIATE,
    ADVANCED
}

enum class Categories { // TODO add all categories
    FRONTEND,
    BACKEND,
    FULLSTACK,
    MOBILE,
    QA,
    DESIGN,
    DEVOPS,
    PROJECT_MANAGEMENT,
    HR,
    MARKETING,
    SALES,
    FINANCE,
    OTHER
}

enum class ExperienceLevel {
    NO_EXPERIENCE,
    SIX_MONTHS,
    ONE_YEAR,
    ONE_YEAR_AND_HALF,
    TWO_YEARS,
    TWO_YEARS_AND_HALF,
    THREE_YEARS,
    FOUR_YEARS,
    FIVE_YEARS,
    SIX_YEARS,
    SEVEN_YEARS,
    EIGHT_YEARS,
    NINE_YEARS,
    TEN_YEARS,
    MORE_TEN_YEARS
}

enum class Domains {
    ADULT,
    GAMBLING,
    DATING,
    GAME_DEV,
    CRYPTO
}

enum class EmploymentOption {
    REMOTE,
    OFFICE,
    PART_TIME,
    FREELANCE
}

enum class Country { // TODO add all countries
    UKRAINE,
    KAZAKHSTAN,
    POLAND,
    USA,
    CANADA,
    GERMANY,
    FRANCE,
    SPAIN,
    ITALY,
    OTHER
}
