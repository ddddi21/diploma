package com.example.feature_film_details.domain

interface FilmDetailsRepository {

    suspend fun markFilmAsWatched(filmId: Int)
}