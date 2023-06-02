package com.example.feature_main.presentation.nestedFragments.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.feature_main.PatientRouter
import com.example.feature_main.domain.PatientInteractor
import com.example.feature_main.presentation.mapper.PatientIntoPresentationPatientMapper
import com.example.feature_main.presentation.nestedFragments.PatientsListsViewModel
import com.medicalSystem.common.di.viewmodel.ViewModelKey
import com.medicalSystem.common.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
class WillWatchLaterFilmsModule {

    @Provides
    internal fun provideWillWatchLaterFilmsViewModelFromStore(fragment: Fragment, factory: ViewModelProvider.Factory): PatientsListsViewModel {
        return ViewModelProviders.of(fragment, factory).get(PatientsListsViewModel::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(PatientsListsViewModel::class)
    fun provideWillWatchLaterFilmsViewModel(
        router: PatientRouter,
        interactor: PatientInteractor,
        filmIntoGridFilmMapper: PatientIntoPresentationPatientMapper
    ): ViewModel {
        return PatientsListsViewModel(router, interactor, filmIntoGridFilmMapper)
    }
}