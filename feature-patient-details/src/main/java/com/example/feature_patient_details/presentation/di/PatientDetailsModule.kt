package com.example.feature_patient_details.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.feature_patient_details.PatientDetailsRouter
import com.example.feature_patient_details.domain.PatientDetailsInteractor
import com.example.feature_patient_details.presentation.PatientDetailsViewModel
import com.medicalSystem.common.di.viewmodel.ViewModelKey
import com.medicalSystem.common.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
class PatientDetailsModule {

    @Provides
    internal fun provideFilmDetailsViewModelFromStore(fragment: Fragment, factory: ViewModelProvider.Factory): PatientDetailsViewModel {
        return ViewModelProviders.of(fragment, factory).get(PatientDetailsViewModel::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(PatientDetailsViewModel::class)
    fun provideFilmDetailsViewModel(router: PatientDetailsRouter, interactor: PatientDetailsInteractor): ViewModel {
        return PatientDetailsViewModel(router, interactor)
    }
}