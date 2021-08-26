package com.example.feature_film_details.di

import com.technokratos.common.data.network.NetworkApiCreator
import com.technokratos.common.di.AuthorizedRetrofit

interface FilmDetailsFeatureDependencies {

    @AuthorizedRetrofit
    fun provideUnauthorizedNetworkApiCreator(): NetworkApiCreator
}