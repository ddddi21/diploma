package com.example.feature_film_details.data.network

import retrofit2.http.PUT
import retrofit2.http.Path

interface FilmDetailsApi {

    @PUT("users/films/{film-id}")
    suspend fun markFilmIsWatched(@Path("film-id") filmId: Int)
}