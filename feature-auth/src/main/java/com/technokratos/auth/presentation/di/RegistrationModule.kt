package com.technokratos.auth.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.technokratos.auth.presentation.registration.RegistrationViewModel
import com.technokratos.auth.router.RegistrationRouter
import com.technokratos.common.di.viewmodel.ViewModelKey
import com.technokratos.common.di.viewmodel.ViewModelModule
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
    fun provideRegistrationViewModelFromStore(router: RegistrationRouter): ViewModel {
        return RegistrationViewModel(router)
    }
}