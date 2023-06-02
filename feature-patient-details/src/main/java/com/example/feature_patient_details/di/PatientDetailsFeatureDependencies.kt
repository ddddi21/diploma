package com.example.feature_patient_details.di

import com.medicalSystem.common.data.network.NetworkApiCreator
import com.medicalSystem.common.di.AuthorizedRetrofit

interface PatientDetailsFeatureDependencies {

    @AuthorizedRetrofit
    fun provideUnauthorizedNetworkApiCreator(): NetworkApiCreator
}