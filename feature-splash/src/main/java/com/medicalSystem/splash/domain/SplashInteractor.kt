package com.medicalSystem.splash.domain

import com.medicalSystem.common.local.sp.UserSharedPreferences
import javax.inject.Inject

class SplashInteractor @Inject constructor(
    private val userSharedPreferences: UserSharedPreferences
) {
    fun isUserLoggedIn(): Boolean {
        return !userSharedPreferences.userAuthToken.isNullOrEmpty()
    }

    fun getUserRefreshToken(): String? {
        return userSharedPreferences.userRefreshToken
    }
}