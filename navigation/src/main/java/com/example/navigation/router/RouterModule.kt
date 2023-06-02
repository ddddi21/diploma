package com.example.navigation.router

import com.example.feature_main.PatientRouter
import com.example.feature_patient_details.PatientDetailsRouter
import com.medicalSystem.auth.router.AuthRouter
import com.medicalSystem.common.di.scope.ApplicationScope
import com.medicalSystem.common.router.NavigateBackRouter
import com.medicalSystem.splash.SplashRouter
import dagger.Binds
import dagger.Module

@Module
abstract class RouterModule {

    @ApplicationScope
    @Binds
    abstract fun provideSplashRouter(routerImpl: RouterImpl): SplashRouter

    @ApplicationScope
    @Binds
    abstract fun provideAuthRouter(routerImpl: RouterImpl): AuthRouter

    @ApplicationScope
    @Binds
    abstract fun provideNavigateBackRouter(routerImpl: RouterImpl): NavigateBackRouter

    @ApplicationScope
    @Binds
    abstract fun provideMainRouter(routerImpl: RouterImpl): MainRouter

    @ApplicationScope
    @Binds
    abstract fun provideCollectionRouter(routerImpl: RouterImpl): PatientRouter

    @ApplicationScope
    @Binds
    abstract fun provideFilmDetailsRouter(routerImpl: RouterImpl): PatientDetailsRouter
}