package com.example.feature_main.di

import com.example.feature_main.data.network.PatientApi
import com.example.feature_main.data.repository.PatientRepositoryImpl
import com.example.feature_main.domain.PatientRepository
import com.medicalSystem.common.data.network.NetworkApiCreator
import com.medicalSystem.common.di.AuthorizedRetrofit
import com.medicalSystem.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class PatientFeatureModule {

    @Provides
    @FeatureScope
    fun provideCollectionApi(@AuthorizedRetrofit apiCreator: NetworkApiCreator): PatientApi {
        return apiCreator.create(PatientApi::class.java)
    }

    @Provides
    @FeatureScope
    fun provideCollectionRepository(collectionRepository: PatientRepositoryImpl): PatientRepository = collectionRepository
}