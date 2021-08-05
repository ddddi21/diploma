package com.technokratos.auth.di.registration

import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.di.FeatureApiHolder
import com.technokratos.common.di.FeatureContainer
import com.technokratos.common.di.scope.ApplicationScope
import com.technokratos.common.router.NavigateBackRouter
import javax.inject.Inject

@ApplicationScope
class RegistrationFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val authRouter: AuthRouter,
    private val navigateBackRouter: NavigateBackRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val registrationFeatureDependencies = DaggerRegistrationFeatureComponent_RegistrationFeatureDependenciesComponent.builder()
            .commonApi(commonApi())
            .build()
        return DaggerRegistrationFeatureComponent.builder()
            .withDependencies(registrationFeatureDependencies)
            .authRouter(authRouter)
            .navigateBackRouter(navigateBackRouter)
            .build()
    }
}