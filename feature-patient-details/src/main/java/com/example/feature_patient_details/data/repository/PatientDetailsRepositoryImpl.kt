package com.example.feature_patient_details.data.repository

import com.example.feature_patient_details.data.network.FilmDetailsApi
import com.example.feature_patient_details.domain.PatientDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PatientDetailsRepositoryImpl @Inject constructor(
    private val api: FilmDetailsApi
) : PatientDetailsRepository {

    override suspend fun markFilmAsWatched(filmId: Int) {
        return withContext(Dispatchers.IO) {
            api.markFilmIsWatched(filmId)
        }
    }
}