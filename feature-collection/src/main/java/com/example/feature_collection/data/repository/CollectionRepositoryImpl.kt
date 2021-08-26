package com.example.feature_collection.data.repository

import com.example.feature_collection.data.network.CollectionApi
import com.example.feature_collection.data.network.mapper.FilmEntityIntoModelMapper
import com.example.feature_collection_api.domain.model.Film
import com.example.feature_collection.domain.CollectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val collectionApi: CollectionApi,
    private val filmEntityIntoModelMapper: FilmEntityIntoModelMapper
) : CollectionRepository {

    override suspend fun getUserFilms(watched: Boolean): List<Film> {
        return withContext(Dispatchers.IO) {
            collectionApi.getUserFilms(watched).filmDtos.map {
                filmEntityIntoModelMapper.map(it)
            }
        }
    }
}