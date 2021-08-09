package com.example.feature_collection.di

import com.example.feature_collection.CollectionRouter
import com.technokratos.common.di.FeatureApiHolder
import com.technokratos.common.di.FeatureContainer
import com.technokratos.common.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class CollectionFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val collectionRouter: CollectionRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val collectionFeatureDependencies = DaggerCollectionFeatureComponent_CollectionFeatureDependenciesComponent.builder()
            .commonApi(commonApi())
            .build()
        return DaggerCollectionFeatureComponent.builder()
            .withDependencies(collectionFeatureDependencies)
            .router(collectionRouter)
            .build()
    }
}