package com.medicalSystem.splash.di

import com.medicalSystem.common.di.CommonApi
import com.medicalSystem.common.di.scope.FeatureScope
import com.medicalSystem.splash.SplashRouter
import com.medicalSystem.splash.presentation.di.SplashComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        SplashFeatureDependencies::class
    ],
    modules = [
        SplashFeatureModule::class
    ]
)
@FeatureScope
interface SplashFeatureComponent : SplashFeatureKey {

    fun splashComponentFactory(): SplashComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(splashRouter: SplashRouter): Builder

        fun withDependencies(deps: SplashFeatureDependencies): Builder

        fun build(): SplashFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface SplashFeatureDependenciesComponent : SplashFeatureDependencies
}