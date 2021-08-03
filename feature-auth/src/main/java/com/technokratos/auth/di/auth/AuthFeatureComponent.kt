package com.technokratos.auth.di.auth

import com.technokratos.auth.presentation.di.AuthComponent
import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.di.CommonApi
import com.technokratos.common.di.scope.FeatureScope
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
interface AuthFeatureComponent {

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