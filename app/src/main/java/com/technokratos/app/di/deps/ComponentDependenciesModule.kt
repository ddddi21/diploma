package com.technokratos.app.di.deps

import com.technokratos.app.di.app.AppComponent
import com.technokratos.app.di.main.MainDependencies
import com.technokratos.common.base.ComponentDependencies
import com.technokratos.common.base.ComponentDependenciesKey
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