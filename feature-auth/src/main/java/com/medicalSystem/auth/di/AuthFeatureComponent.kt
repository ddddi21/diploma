package com.medicalSystem.auth.di

import com.medicalSystem.auth.presentation.di.AuthComponent
import com.medicalSystem.auth.presentation.di.RegistrationComponent
import com.medicalSystem.auth.router.AuthRouter
import com.medicalSystem.common.di.CommonApi
import com.medicalSystem.common.di.scope.FeatureScope
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        AuthFeatureDependencies::class
    ],
    modules = [
        AuthFeatureModule::class
    ]
)
@FeatureScope
interface AuthFeatureComponent : AuthFeatureKey {

    fun registrationComponentFactory(): RegistrationComponent.Factory

    fun authComponentFactory(): AuthComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(authRouter: AuthRouter): Builder

        fun withDependencies(deps: AuthFeatureDependencies): Builder

        fun build(): AuthFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface AuthFeatureDependenciesComponent : AuthFeatureDependencies
}