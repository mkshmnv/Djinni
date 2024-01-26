package com.mkshmnv.djinni.model.vacancy

import com.google.gson.annotations.SerializedName

data class Vacancy(
    @SerializedName("category") var category: String = "",
    @SerializedName("contactName") var contactName: String = "",
    @SerializedName("description") var description: String = "",
    @SerializedName("employerName") var employerName: String = "",
    @SerializedName("id") var id: Int = 0,
    @SerializedName("publishedDate") var publishedDate: String = "",
    @SerializedName("reviews") var reviews: Int = 0,
    @SerializedName("title") var title: String = "",
    @SerializedName("views") var views: Int = 0
//    val english: EnglishLevel,
//    val salaryExpectations: Int,
//    val experience: ExperienceLevel,
//    val domain: List<Domains>,
//    val employmentType: List<EmploymentOption>,
//    val location: Country,
//    val candidateFrom: List<Country>
)