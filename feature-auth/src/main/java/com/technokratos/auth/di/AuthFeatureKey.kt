package com.technokratos.auth.di

import com.technokratos.auth.domain.AuthRepository

interface AuthFeatureKey {

    fun provideAuthRepository(): AuthRepository
}