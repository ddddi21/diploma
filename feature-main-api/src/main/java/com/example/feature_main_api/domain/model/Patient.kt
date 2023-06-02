package com.example.feature_main_api.domain.model

import java.io.Serializable

data class Patient(
    val id: Int,
    val name: String = "",
    val age: String = "",
    val sex: String = "",
    val illness: String = "",
    val medicaments: String = "",
    ) : Serializable

