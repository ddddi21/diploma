package com.example.feature_film_details.di

import com.example.feature_film_details.data.network.FilmDetailsApi
import com.example.feature_film_details.data.repository.FilmDetailsRepositoryImpl
import com.example.feature_film_details.domain.FilmDetailsRepository
import com.technokratos.common.data.network.NetworkApiCreator
import com.technokratos.common.di.AuthorizedRetrofit
import com.technokratos.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class FilmDetailsFeatureModule {

    @Provides
    @FeatureScope
    fun provideFilmDetailsApi(@AuthorizedRetrofit apiCreator: NetworkApiCreator): FilmDetailsApi {
        return apiCreator.create(FilmDetailsApi::class.java)
    }

    @Provides
    @FeatureScope
    fun provideFilmDetailsRepository(filmDetailsRepository: FilmDetailsRepositoryImpl): FilmDetailsRepository = filmDetailsRepository
}