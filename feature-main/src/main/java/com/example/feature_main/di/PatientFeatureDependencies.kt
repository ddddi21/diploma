package com.example.feature_main.di

import com.medicalSystem.common.data.network.NetworkApiCreator
import com.medicalSystem.common.di.AuthorizedRetrofit

interface PatientFeatureDependencies {

    @AuthorizedRetrofit
    fun provideUnauthorizedNetworkApiCreator(): NetworkApiCreator
}