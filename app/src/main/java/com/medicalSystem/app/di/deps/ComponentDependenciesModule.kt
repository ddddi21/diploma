package com.medicalSystem.app.di.deps

import com.medicalSystem.app.di.app.AppComponent
import com.medicalSystem.app.di.main.MainDependencies
import com.medicalSystem.common.base.ComponentDependencies
import com.medicalSystem.common.base.ComponentDependenciesKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ComponentDependenciesModule {

    @Binds
    @IntoMap
    @ComponentDependenciesKey(MainDependencies::class)
    fun provideMainDependencies(component: AppComponent): ComponentDependencies
}