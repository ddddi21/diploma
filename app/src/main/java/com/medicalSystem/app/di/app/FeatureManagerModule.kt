package com.medicalSystem.app.di.app

import com.medicalSystem.app.di.deps.FeatureHolderManager
import com.medicalSystem.common.di.FeatureApiHolder
import com.medicalSystem.common.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class FeatureManagerModule {

    @ApplicationScope
    @Provides
    fun provideFeatureHolderManager(featureApiHolderMap: @JvmSuppressWildcards Map<Class<*>, FeatureApiHolder>): FeatureHolderManager {
        return FeatureHolderManager(featureApiHolderMap)
    }
}