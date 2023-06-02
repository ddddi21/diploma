package com.example.feature_patient_details.di

import com.example.feature_patient_details.data.network.FilmDetailsApi
import com.example.feature_patient_details.data.repository.PatientDetailsRepositoryImpl
import com.example.feature_patient_details.domain.PatientDetailsRepository
import com.medicalSystem.common.data.network.NetworkApiCreator
import com.medicalSystem.common.di.AuthorizedRetrofit
import com.medicalSystem.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class PatientDetailsFeatureModule {

    @Provides
    @FeatureScope
    fun provideFilmDetailsApi(@AuthorizedRetrofit apiCreator: NetworkApiCreator): FilmDetailsApi {
        return apiCreator.create(FilmDetailsApi::class.java)
    }

    @Provides
    @FeatureScope
    fun provideFilmDetailsRepository(filmDetailsRepository: PatientDetailsRepositoryImpl): PatientDetailsRepository = filmDetailsRepository
}