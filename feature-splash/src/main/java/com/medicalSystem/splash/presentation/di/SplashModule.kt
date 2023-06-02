package com.medicalSystem.splash.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.medicalSystem.common.di.viewmodel.ViewModelKey
import com.medicalSystem.common.di.viewmodel.ViewModelModule
import com.medicalSystem.splash.SplashRouter
import com.medicalSystem.splash.domain.SplashInteractor
import com.medicalSystem.splash.presentation.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
class SplashModule {

    @Provides
    internal fun provideSplashViewModelFromStore(fragment: Fragment, factory: ViewModelProvider.Factory): SplashViewModel {
        return ViewModelProviders.of(fragment, factory).get(SplashViewModel::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun provideSplashViewModelFromStore(router: SplashRouter, splashInteractor: SplashInteractor): ViewModel {
        return SplashViewModel(router, splashInteractor)
    }
}