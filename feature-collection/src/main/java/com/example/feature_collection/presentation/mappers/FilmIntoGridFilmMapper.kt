package com.example.feature_collection.presentation.mappers

import com.example.feature_collection.data.network.model.Film
import com.example.feature_collection.model.FilmGridItem
import javax.inject.Inject

private const val DEFAULT_POSTER_URL = "https://upload.wikimedia.org/wikipedia/ru/thumb/6/6e/%D0%9E%D1%81%D1%82%D1%80%D0%9A%D0%BE%D0%B7.jpg/274px-%D0%9E%D1%81%D1%82%D1%80%D0%9A%D0%BE%D0%B7.jpg"

class FilmIntoGridFilmMapper @Inject constructor() {

    fun map(model: Film, onItemClicked: ((Int) -> Unit)? = null): FilmGridItem {
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
}