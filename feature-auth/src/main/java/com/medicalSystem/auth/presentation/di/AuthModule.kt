package com.medicalSystem.auth.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.medicalSystem.auth.domain.AuthInteractor
import com.medicalSystem.auth.router.AuthRouter
import com.medicalSystem.auth.presentation.auth.AuthViewModel
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
class AuthModule {

    @Provides
    internal fun provideAuthViewModelFromStore(fragment: Fragment, factory: ViewModelProvider.Factory): AuthViewModel {
        return ViewModelProviders.of(fragment, factory).get(AuthViewModel::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun provideAuthViewModelFromStore(router: AuthRouter, interactor: AuthInteractor, resourceManager: ResourceManager): ViewModel {
        return AuthViewModel(router, interactor, resourceManager)
    }
}