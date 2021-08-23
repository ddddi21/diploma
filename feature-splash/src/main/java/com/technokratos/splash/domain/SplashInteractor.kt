package com.technokratos.splash.domain

import com.technokratos.auth.domain.AuthRepository
import javax.inject.Inject

class SplashInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun getUserAuthToken(): Result<String?> = runCatching {
        authRepository.getUserAuthToken()
    }

    suspend fun getUserRefreshToken(): Result<String?> = runCatching {
        authRepository.getUserRefreshToken()
    }
}