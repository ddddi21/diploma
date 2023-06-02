package com.medicalSystem.auth.di

import com.medicalSystem.auth.router.AuthRouter
import com.medicalSystem.common.di.FeatureApiHolder
import com.medicalSystem.common.di.FeatureContainer
import com.medicalSystem.common.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class AuthFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val authRouter: AuthRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val authFeatureDependencies = DaggerAuthFeatureComponent_AuthFeatureDependenciesComponent.builder()
            .commonApi(commonApi())
            .build()
        return DaggerAuthFeatureComponent.builder()
            .withDependencies(authFeatureDependencies)
            .router(authRouter)
            .build()
    }
}