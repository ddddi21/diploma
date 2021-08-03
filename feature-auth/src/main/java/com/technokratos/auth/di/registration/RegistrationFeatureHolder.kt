package com.technokratos.auth.di.registration

import com.technokratos.auth.router.RegistrationRouter
import com.technokratos.common.di.FeatureApiHolder
import com.technokratos.common.di.FeatureContainer
import com.technokratos.common.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class RegistrationFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val registrationRouter: RegistrationRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val registrationFeatureDependencies = DaggerRegistrationFeatureComponent_RegistrationFeatureDependenciesComponent.builder()
            .commonApi(commonApi())
            .build()
        return DaggerRegistrationFeatureComponent.builder()
            .withDependencies(registrationFeatureDependencies)
            .router(registrationRouter)
            .build()
    }
}