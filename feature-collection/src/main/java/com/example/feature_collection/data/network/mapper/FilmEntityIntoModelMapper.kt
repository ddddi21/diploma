package com.example.feature_collection.data.network.mapper

import com.example.feature_collection.data.network.response.FilmDto
import com.example.feature_collection.data.network.model.Film
import com.technokratos.common.utils.BaseSingleModelMapper
import javax.inject.Inject

private const val DEFAULT_POSTER_URL = "https://upload.wikimedia.org/wikipedia/ru/thumb/6/6e/%D0%9E%D1%81%D1%82%D1%80%D0%9A%D0%BE%D0%B7.jpg/274px-%D0%9E%D1%81%D1%82%D1%80%D0%9A%D0%BE%D0%B7.jpg"

class FilmEntityIntoModelMapper @Inject constructor() : BaseSingleModelMapper<FilmDto, Film> {

    override fun map(model: FilmDto): Film {
        return Film(
            id = model.id,
            title = model.title,
            description = model.description,
            genres = model.genres,
            rating = model.rating,
            posterUrl = model.posterUrl ?: DEFAULT_POSTER_URL
        )
    }
}