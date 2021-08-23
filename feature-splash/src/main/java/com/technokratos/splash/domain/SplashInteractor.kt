package com.technokratos.splash.domain

import com.technokratos.auth.domain.AuthRepository
import javax.inject.Inject

class SplashInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {

    fun isUserLoggedIn(): Boolean {
        return !authRepository.getUserAuthToken().isNullOrEmpty()
    }

    fun getUserRefreshToken(): String? {
        return authRepository.getUserRefreshToken()
    }
}