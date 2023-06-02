package com.example.feature_main.presentation.mapper

import com.example.feature_main_api.domain.model.Film
import com.example.feature_main.model.FilmGridItem
import com.example.feature_main.model.PatientLinearItem
import com.example.feature_main_api.domain.model.Patient
import javax.inject.Inject

private const val DEFAULT_POSTER_URL = "https://upload.wikimedia.org/wikipedia/ru/thumb/6/6e/%D0%9E%D1%81%D1%82%D1%80%D0%9A%D0%BE%D0%B7.jpg/274px-%D0%9E%D1%81%D1%82%D1%80%D0%9A%D0%BE%D0%B7.jpg"

class PatientIntoPresentationPatientMapper @Inject constructor() {

    fun mapIntoGridFilm(model: Film, onItemClicked: (() -> Unit)? = null): FilmGridItem {
        return FilmGridItem(
            id = model.id,
            title = model.title,
            description = model.description ?: "",
            genres = model.genres ?: listOf(""),
            rating = model.rating ?: 0.0,
            posterUrl = model.posterUrl ?: DEFAULT_POSTER_URL,
            onItemClicked = onItemClicked
        )
    }

    fun mapIntoLinearPatient(model: Patient, onItemClicked: (() -> Unit)? = null): PatientLinearItem {
        return PatientLinearItem(
            id = model.id,
            name = model.name,
            onItemClicked = onItemClicked
        )
    }

    fun mapIntoLinearDiagnos(model: String, onItemClicked: (() -> Unit)? = null): PatientLinearItem {
        return PatientLinearItem(
            id = 0,
            name = model,
            onItemClicked = onItemClicked
        )
    }
}