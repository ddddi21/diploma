package com.example.feature_collection.domain

import com.example.feature_collection.data.network.model.Film
import javax.inject.Inject

class CollectionInteractor @Inject constructor(
    private val collectionRepository: CollectionRepository
) {

    suspend fun getUserFilms(watched: Boolean): Result<List<Film>> = runCatching {
        collectionRepository.getUserFilms(watched)
    }
}