package com.medicalSystem.auth.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.medicalSystem.auth.domain.AuthInteractor
import com.medicalSystem.auth.presentation.registration.RegistrationViewModel
import com.medicalSystem.auth.router.AuthRouter
import com.medicalSystem.common.di.viewmodel.ViewModelKey
import com.medicalSystem.common.di.viewmodel.ViewModelModule
import com.medicalSystem.common.resources.ResourceManager
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
class RegistrationModule {

    @Provides
    internal fun provideRegistrationViewModelFromStore(fragment: Fragment, factory: ViewModelProvider.Factory): RegistrationViewModel {
        return ViewModelProviders.of(fragment, factory).get(RegistrationViewModel::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun provideRegistrationViewModelFromStore(authRouter: AuthRouter, interactor: AuthInteractor, resourceManager: ResourceManager): ViewModel {
        return RegistrationViewModel(authRouter, interactor, resourceManager)
    }
}