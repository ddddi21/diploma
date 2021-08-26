package com.example.feature_collection.data.network

import com.example.feature_collection.data.network.response.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CollectionApi {

    @GET("users/films")
    suspend fun getUserFilms(@Query(value = "watched") watched: Boolean? = false): FilmResponse
}