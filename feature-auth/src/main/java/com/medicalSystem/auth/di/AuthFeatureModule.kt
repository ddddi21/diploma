package com.medicalSystem.auth.di

import com.medicalSystem.auth.data.network.AuthApi
import com.medicalSystem.auth.data.repository.AuthRepositoryImpl
import com.medicalSystem.auth.domain.AuthRepository
import com.medicalSystem.common.data.network.NetworkApiCreator
import com.medicalSystem.common.di.UnauthorizedRetrofit
import com.medicalSystem.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class AuthFeatureModule {

    @Provides
    @FeatureScope
    fun provideAuthApi(@UnauthorizedRetrofit apiCreator: NetworkApiCreator): AuthApi {
        return apiCreator.create(AuthApi::class.java)
    }

    @Provides
    @FeatureScope
    fun provideAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository = authRepository
}