package com.example.feature_collection.di

import com.example.feature_collection.CollectionRouter
import com.example.feature_collection.presentation.di.CollectionComponent
import com.example.feature_collection.presentation.watchedFilmCollection.di.WatchedCollectionFilmsComponent
import com.example.feature_collection.presentation.watchLaterFilmCollection.di.WillWatchLaterFilmsComponent
import com.technokratos.common.di.CommonApi
import com.technokratos.common.di.scope.FeatureScope
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        CollectionFeatureDependencies::class
    ],
    modules = [
        CollectionFeatureModule::class
    ]
)
@FeatureScope
interface CollectionFeatureComponent : CollectionFeatureKey {

    fun collectionComponentFactory(): CollectionComponent.Factory

    fun watchedCollectionFilmsFactory(): WatchedCollectionFilmsComponent.Factory

    fun willWatchLaterFilmsFactory(): WillWatchLaterFilmsComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(collectionRouter: CollectionRouter): Builder

        fun withDependencies(deps: CollectionFeatureDependencies): Builder

        fun build(): CollectionFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface CollectionFeatureDependenciesComponent : CollectionFeatureDependencies
}