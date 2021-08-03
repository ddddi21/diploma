package com.technokratos.auth.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.technokratos.auth.router.AuthRouter
import com.technokratos.auth.presentation.auth.AuthViewModel
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
class AuthModule {

    @Provides
    internal fun provideAuthViewModelFromStore(fragment: Fragment, factory: ViewModelProvider.Factory): AuthViewModel {
        return ViewModelProviders.of(fragment, factory).get(AuthViewModel::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun provideAuthViewModelFromStore(router: AuthRouter): ViewModel {
        return AuthViewModel(router)
    }
}