package com.example.feature_main.data.repository

import com.example.feature_main.data.network.PatientApi
import com.example.feature_main.data.network.mapper.PatientEntityIntoModelMapper
import com.example.feature_main_api.domain.model.Film
import com.example.feature_main.domain.PatientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PatientRepositoryImpl @Inject constructor(
    private val patientApi: PatientApi,
    private val patientEntityIntoModelMapper: PatientEntityIntoModelMapper
) : PatientRepository {

    override suspend fun getUserFilms(watched: Boolean): List<Film> {
        return withContext(Dispatchers.IO) {
            patientApi.getUserFilms(watched).filmDtos.map {
                patientEntityIntoModelMapper.map(it)
            }
        }
    }
}