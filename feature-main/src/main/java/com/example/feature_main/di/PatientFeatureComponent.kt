package com.example.feature_main.di

import com.example.feature_main.PatientRouter
import com.example.feature_main.presentation.di.PatientComponent
import com.example.feature_main.presentation.nestedFragments.di.WillWatchLaterFilmsComponent
import com.medicalSystem.common.di.CommonApi
import com.medicalSystem.common.di.scope.FeatureScope
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        PatientFeatureDependencies::class
    ],
    modules = [
        PatientFeatureModule::class
    ]
)
@FeatureScope
interface PatientFeatureComponent : PatientFeatureKey {

    fun patientComponentFactory(): PatientComponent.Factory

    fun willWatchLaterFilmsFactory(): WillWatchLaterFilmsComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(patientRouter: PatientRouter): Builder

        fun withDependencies(deps: PatientFeatureDependencies): Builder

        fun build(): PatientFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface PatientFeatureDependenciesComponent : PatientFeatureDependencies
}