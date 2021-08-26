package com.example.feature_film_details.domain

interface FilmDetailsRepository {

    suspend fun markFilmIsWatched(filmId: Int)

    suspend fun getFilmDetails(filmId: Int)
}