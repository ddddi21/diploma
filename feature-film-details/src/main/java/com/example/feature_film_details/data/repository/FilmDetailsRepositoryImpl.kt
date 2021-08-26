package com.example.feature_film_details.data.repository

import com.example.feature_film_details.data.network.FilmDetailsApi
import com.example.feature_film_details.domain.FilmDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilmDetailsRepositoryImpl @Inject constructor(
    private val filmDetailsApi: FilmDetailsApi
) : FilmDetailsRepository{

    override suspend fun markFilmIsWatched(filmId: Int) {
        return withContext(Dispatchers.IO) {
            filmDetailsApi.markFilmIsWatched(filmId)
        }
    }

    override suspend fun getFilmDetails(filmId: Int) {

    }
}