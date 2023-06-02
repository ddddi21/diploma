package com.medicalSystem.auth.data.repository

import android.util.Log
import com.medicalSystem.auth.data.network.AuthApi
import com.medicalSystem.auth.data.network.mappers.TokenMapper
import com.medicalSystem.auth.data.network.response.AuthDto
import com.medicalSystem.auth.data.network.response.EmailDto
import com.medicalSystem.auth.data.network.response.RefreshTokenDto
import com.medicalSystem.auth.domain.AuthRepository
import com.medicalSystem.common.local.sp.UserSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val userSharedPreferences: UserSharedPreferences,
    private val tokenMapper: TokenMapper
) : AuthRepository {

    override suspend fun signUp(email: String, password: String) {
        withContext(Dispatchers.IO) {
            authApi.signUp(AuthDto(email, password))
        }
    }

    override suspend fun signIn(email: String, password: String) {
        withContext(Dispatchers.IO) {
            authApi.signIn(AuthDto(email, password)).let { tokenDto ->
                val tokens = tokenMapper.map(tokenDto)
                userSharedPreferences.userAuthToken = tokens.authToken
                userSharedPreferences.userRefreshToken = tokens.refreshToken
                Log.d("AWESOME", "auth token = ${tokens.authToken}, refreshToken = ${tokens.refreshToken} in prefs")
            }
        }
    }

    override suspend fun reset(email: String) {
        withContext(Dispatchers.IO) {
            authApi.resetPassword(EmailDto(email))
        }
    }

    override suspend fun updateTokens(refreshToken: String) {
        withContext(Dispatchers.IO) {
            authApi.updateTokens(RefreshTokenDto(refreshToken)).let { tokenDto ->
                val tokens = tokenMapper.map(tokenDto)
                userSharedPreferences.userAuthToken = tokens.authToken
                userSharedPreferences.userRefreshToken = tokens.refreshToken
            }
        }
    }
}