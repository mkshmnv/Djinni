package com.mkshmnv.djinni.model.employer

data class Employer(
    val name: String,
    val logo: String,
    val description: String,
    val website: String,
    val type: TypeOfEmployer
)

enum class TypeOfEmployer {
    AGENCY,
    OUTSOURCE,
    OUTSTAFF,
    PRODUCT,
    STARTUP
}