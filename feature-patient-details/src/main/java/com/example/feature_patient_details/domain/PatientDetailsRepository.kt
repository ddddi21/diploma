package com.example.feature_patient_details.domain

interface PatientDetailsRepository {

    suspend fun markFilmAsWatched(filmId: Int)
}