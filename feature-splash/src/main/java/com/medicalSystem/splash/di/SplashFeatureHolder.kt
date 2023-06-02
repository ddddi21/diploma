package com.medicalSystem.splash.di

import com.medicalSystem.common.di.FeatureApiHolder
import com.medicalSystem.common.di.FeatureContainer
import com.medicalSystem.common.di.scope.ApplicationScope
import com.medicalSystem.splash.SplashRouter
import javax.inject.Inject

@ApplicationScope
class SplashFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val splashRouter: SplashRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val splashFeatureDependencies = DaggerSplashFeatureComponent_SplashFeatureDependenciesComponent.builder()
            .commonApi(commonApi())
            .build()
        return DaggerSplashFeatureComponent.builder()
            .withDependencies(splashFeatureDependencies)
            .router(splashRouter)
            .build()
    }
}