package com.example.feature_film_details.di

import com.example.feature_film_details.FilmDetailsRouter
import com.technokratos.common.di.FeatureApiHolder
import com.technokratos.common.di.FeatureContainer
import com.technokratos.common.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class FilmDetailsFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val filmDetailsRouter: FilmDetailsRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val collectionFeatureDependencies = DaggerFilmDetailsFeatureComponent_FilmDetailsFeatureDependenciesComponent.builder()
            .commonApi(commonApi())
            .build()
        return DaggerFilmDetailsFeatureComponent.builder()
            .withDependencies(collectionFeatureDependencies)
            .router(filmDetailsRouter)
            .build()
    }
}