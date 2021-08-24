package com.technokratos.splash.di

import com.technokratos.auth.domain.AuthRepository

interface SplashFeatureDependencies {

    fun provideAuthRepository(): AuthRepository
}