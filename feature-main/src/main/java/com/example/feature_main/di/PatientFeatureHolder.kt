package com.example.feature_main.di

import com.example.feature_main.PatientRouter
import com.medicalSystem.common.di.FeatureApiHolder
import com.medicalSystem.common.di.FeatureContainer
import com.medicalSystem.common.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class PatientFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val patientRouter: PatientRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val collectionFeatureDependencies = DaggerPatientFeatureComponent_PatientFeatureDependenciesComponent.builder()
            .commonApi(commonApi())
            .build()
        return DaggerPatientFeatureComponent.builder()
            .withDependencies(collectionFeatureDependencies)
            .router(patientRouter)
            .build()
    }
}