package com.medicalSystem.splash.di

import com.medicalSystem.common.local.sp.UserSharedPreferences

interface SplashFeatureDependencies {

    fun provideUserSharedPreferences(): UserSharedPreferences
}