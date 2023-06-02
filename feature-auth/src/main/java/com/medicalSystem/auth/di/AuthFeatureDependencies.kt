package com.medicalSystem.auth.di

import com.medicalSystem.common.data.network.NetworkApiCreator
import com.medicalSystem.common.di.UnauthorizedRetrofit
import com.medicalSystem.common.local.sp.UserSharedPreferences
import com.medicalSystem.common.resources.ResourceManager

interface AuthFeatureDependencies {

    fun provideUserSharedPreferences(): UserSharedPreferences

    @UnauthorizedRetrofit
    fun provideUnauthorizedNetworkApiCreator(): NetworkApiCreator

    fun provideResourceManager(): ResourceManager
}