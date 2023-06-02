package com.example.feature_patient_details.di

import com.example.feature_patient_details.PatientDetailsRouter
import com.example.feature_patient_details.presentation.di.PatientDetailsComponent
import com.medicalSystem.common.di.CommonApi
import com.medicalSystem.common.di.scope.FeatureScope
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        PatientDetailsFeatureDependencies::class
    ],
    modules = [
        PatientDetailsFeatureModule::class
    ]
)
@FeatureScope
interface PatientDetailsFeatureComponent : PatientDetailsFeatureKey {

    fun filmDetailsComponentFactory(): PatientDetailsComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(patientDetailsRouter: PatientDetailsRouter): Builder

        fun withDependencies(deps: PatientDetailsFeatureDependencies): Builder

        fun build(): PatientDetailsFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface PatientDetailsFeatureDependenciesComponent : PatientDetailsFeatureDependencies
}