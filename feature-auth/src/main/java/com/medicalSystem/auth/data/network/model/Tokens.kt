package com.medicalSystem.auth.data.network.model

data class Tokens(
    val authToken: String,
    val refreshToken: String
)