package com.example.feature_main.data.network

import com.example.feature_main.data.network.response.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PatientApi {

    @GET("users/films")
    suspend fun getUserFilms(@Query(value = "watched") watched: Boolean? = false): FilmResponse
}