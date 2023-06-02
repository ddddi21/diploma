package com.example.feature_main.domain

import com.example.feature_main_api.domain.model.Film

interface PatientRepository {

    suspend fun getUserFilms(watched: Boolean): List<Film>
}