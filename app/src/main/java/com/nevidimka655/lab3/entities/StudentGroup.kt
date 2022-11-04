package com.nevidimka655.lab3.entities

data class StudentGroup(
    val name: String,
    val facultyName: String,
    val educationLevel: Int,
    val flagContractExists: Boolean,
    val flagPrivilageExists: Boolean
)