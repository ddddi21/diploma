package com.example.feature_collection.di

import com.example.feature_collection.data.network.CollectionApi
import com.example.feature_collection.data.repository.CollectionRepositoryImpl
import com.example.feature_collection.domain.CollectionRepository
import com.technokratos.common.data.network.NetworkApiCreator
import com.technokratos.common.di.AuthorizedRetrofit
import com.technokratos.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class CollectionFeatureModule {

    @Provides
    @FeatureScope
    fun provideCollectionApi(@AuthorizedRetrofit apiCreator: NetworkApiCreator): CollectionApi {
        return apiCreator.create(CollectionApi::class.java)
    }

    @Provides
    @FeatureScope
    fun provideCollectionRepository(collectionRepository: CollectionRepositoryImpl): CollectionRepository = collectionRepository
}