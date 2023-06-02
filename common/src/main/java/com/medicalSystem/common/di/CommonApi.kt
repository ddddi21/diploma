package com.medicalSystem.common.di

import android.content.Context
import com.medicalSystem.common.data.network.NetworkApiCreator
import com.medicalSystem.common.local.sp.UserSharedPreferences
import com.medicalSystem.common.resources.ResourceManager

interface CommonApi {

    fun context(): Context

    fun provideResourceManager(): ResourceManager

    @UnauthorizedRetrofit
    fun provideUnauthorizedNetworkApiCreator(): NetworkApiCreator

    @AuthorizedRetrofit
    fun provideAuthorizedNetworkApiCreator(): NetworkApiCreator

    fun provideUserSharedPreferences(): UserSharedPreferences
}