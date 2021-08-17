package com.technokratos.auth.data.network.request

data class Tokens(
    val authToken: String,
    val refreshToken: String
)