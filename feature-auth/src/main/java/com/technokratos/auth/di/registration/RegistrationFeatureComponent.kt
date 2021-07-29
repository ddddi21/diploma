package com.technokratos.auth.di.registration

import com.technokratos.auth.presentation.di.RegistrationComponent
import com.technokratos.auth.router.RegistrationRouter
import com.technokratos.common.di.CommonApi
import com.technokratos.common.di.scope.FeatureScope
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        RegistrationFeatureDependencies::class
    ],
    modules = [
        RegistrationFeatureModule::class
    ]
)
@FeatureScope
interface RegistrationFeatureComponent {

    fun registrationComponentFactory(): RegistrationComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(registrationRouter: RegistrationRouter): Builder

        fun withDependencies(deps: RegistrationFeatureDependencies): Builder

        fun build(): RegistrationFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface RegistrationFeatureDependenciesComponent : RegistrationFeatureDependencies
}