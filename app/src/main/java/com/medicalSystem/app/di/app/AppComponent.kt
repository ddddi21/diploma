package com.medicalSystem.app.di.app

import com.example.navigation.navigation.NavControllerProvider
import com.example.navigation.navigation.NavigationModule
import com.example.navigation.router.RouterModule
import com.medicalSystem.app.App
import com.medicalSystem.app.di.deps.ComponentDependenciesModule
import com.medicalSystem.app.di.deps.ComponentHolderModule
import com.medicalSystem.app.di.main.MainDependencies
import com.medicalSystem.common.di.CommonApi
import com.medicalSystem.common.di.modules.CommonModule
import com.medicalSystem.common.di.modules.NetworkModule
import com.medicalSystem.common.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        AppModule::class,
        CommonModule::class,
        NetworkModule::class,
        RouterModule::class,
        NavigationModule::class,
        ComponentHolderModule::class,
        ComponentDependenciesModule::class,
        FeatureManagerModule::class
    ]
)
interface AppComponent : MainDependencies, CommonApi {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

    fun provideNavControllerProvider(): NavControllerProvider
}