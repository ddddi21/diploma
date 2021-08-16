package com.example.feature_film_details.di

import com.example.feature_film_details.FilmDetailsRouter
import com.example.feature_film_details.presentation.di.FilmDetailsComponent
import com.technokratos.common.di.CommonApi
import com.technokratos.common.di.scope.FeatureScope
import com.technokratos.common.router.NavigateBackRouter
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        FilmDetailsFeatureDependencies::class
    ],
    modules = [
        FilmDetailsFeatureModule::class
    ]
)
@FeatureScope
interface FilmDetailsFeatureComponent : FilmDetailsFeatureKey {

    fun filmDetailsComponentFactory(): FilmDetailsComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(filmDetailsRouter: FilmDetailsRouter): Builder

        @BindsInstance
        fun navigateBackRouter(navigateBackRouter: NavigateBackRouter): Builder

        fun withDependencies(deps: FilmDetailsFeatureDependencies): Builder

        fun build(): FilmDetailsFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface FilmDetailsFeatureDependenciesComponent : FilmDetailsFeatureDependencies
}