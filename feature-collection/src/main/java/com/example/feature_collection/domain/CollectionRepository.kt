package com.example.feature_collection.domain

import com.example.feature_collection_api.domain.model.Film

interface CollectionRepository {

    suspend fun getUserFilms(watched: Boolean): List<Film>
}