package com.example.feature_patient_details.domain

import javax.inject.Inject

class PatientDetailsInteractor @Inject constructor(
    private val repository: PatientDetailsRepository
) {

    suspend fun markFilmAsWatched(filmId: Int): Result<Unit> = runCatching {
        repository.markFilmAsWatched(filmId)
    }
}