package com.technokratos.auth.domain

import com.technokratos.auth.data.network.request.Tokens

interface AuthRepository {

    suspend fun signUp(email: String, password: String)

    suspend fun signIn(email: String, password: String): Tokens

    suspend fun reset(email: String)

    suspend fun updateTokens(authToken: String, refreshToken: String): Tokens

    suspend fun getUserAuthToken(): String?

    suspend fun getUserRefreshToken(): String?
}