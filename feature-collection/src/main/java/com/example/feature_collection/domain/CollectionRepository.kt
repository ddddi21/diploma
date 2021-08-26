package com.example.feature_collection.domain

import com.example.feature_collection.data.network.model.Film

interface CollectionRepository {

    suspend fun getUserFilms(watched: Boolean): List<Film>
}