package com.example.feature_main.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.feature_main.PatientRouter
import com.example.feature_main.domain.PatientInteractor
import com.example.feature_main.presentation.diagnos.DiagnosCheckViewModel
import com.example.feature_main.presentation.mapper.PatientIntoPresentationPatientMapper
import com.example.feature_patient_details.domain.PatientDetailsInteractor
import com.medicalSystem.common.di.viewmodel.ViewModelKey
import com.medicalSystem.common.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
class PatientModule {

    @Provides
    internal fun provideCollectionViewModelFromStore(fragment: Fragment, factory: ViewModelProvider.Factory): DiagnosCheckViewModel {
        return ViewModelProviders.of(fragment, factory).get(DiagnosCheckViewModel::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(DiagnosCheckViewModel::class)
    fun provideCollectionViewModel(router: PatientRouter, interactor: PatientInteractor, mapper: PatientIntoPresentationPatientMapper): ViewModel {
        return DiagnosCheckViewModel(router, interactor, mapper)
    }
}