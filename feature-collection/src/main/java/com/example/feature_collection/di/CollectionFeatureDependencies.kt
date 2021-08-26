package com.example.feature_collection.di

import com.technokratos.common.data.network.NetworkApiCreator
import com.technokratos.common.di.AuthorizedRetrofit

interface CollectionFeatureDependencies {

    @AuthorizedRetrofit
    fun provideUnauthorizedNetworkApiCreator(): NetworkApiCreator
}