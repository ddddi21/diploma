package com.example.navigation.navigation

import com.medicalSystem.common.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
object NavigationModule {

    @JvmStatic
    @ApplicationScope
    @Provides
    fun provideNavControllerProvider(): NavControllerProvider = NavControllerProvider()
}