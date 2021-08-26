package com.example.feature_film_details.domain

import javax.inject.Inject

class FilmDetailsInteractor @Inject constructor(
    private val repository: FilmDetailsRepository
) {

    suspend fun markFilmIsWatched(filmId: Int): Result<Unit> = runCatching {
        repository.markFilmIsWatched(filmId)
    }
}