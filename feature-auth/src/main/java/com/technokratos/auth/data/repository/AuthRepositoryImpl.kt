package com.technokratos.auth.data.repository

import com.technokratos.auth.data.network.AuthApi
import com.technokratos.auth.data.network.request.Tokens
import com.technokratos.auth.data.network.response.AuthDto
import com.technokratos.auth.data.network.response.EmailDto
import com.technokratos.auth.data.network.response.TokenDto
import com.technokratos.auth.domain.AuthRepository
import com.technokratos.common.local.sp.UserSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val userSharedPreferences: UserSharedPreferences
) : AuthRepository {

    override suspend fun signUp(email: String, password: String) {
        withContext(Dispatchers.IO) {
            authApi.signUp(AuthDto(email, password))
        }
    }

    override suspend fun signIn(email: String, password: String): Tokens {
        withContext(Dispatchers.IO) {
            authApi.signIn(AuthDto(email, password))
        }
    }

    override suspend fun reset(email: String) {
        withContext(Dispatchers.IO) {
            authApi.resetPassword(EmailDto(email))
        }
    }

    override suspend fun updateTokens(authToken: String, refreshToken: String): Tokens {
        withContext(Dispatchers.IO) {
            authApi.updateTokens(authToken, TokenDto(refreshToken))
        }
    }

    override suspend fun getUserAuthToken() = userSharedPreferences.userAuthToken

    override suspend fun getUserRefreshToken() = userSharedPreferences.userRefreshToken

}