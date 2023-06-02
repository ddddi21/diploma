package com.example.feature_patient_details.di

import com.example.feature_patient_details.PatientDetailsRouter
import com.medicalSystem.common.di.FeatureApiHolder
import com.medicalSystem.common.di.FeatureContainer
import com.medicalSystem.common.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class PatientDetailsFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val patientDetailsRouter: PatientDetailsRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val collectionFeatureDependencies = DaggerPatientDetailsFeatureComponent_PatientDetailsFeatureDependenciesComponent.builder()
            .commonApi(commonApi())
            .build()
        return DaggerPatientDetailsFeatureComponent.builder()
            .withDependencies(collectionFeatureDependencies)
            .router(patientDetailsRouter)
            .build()
    }
}