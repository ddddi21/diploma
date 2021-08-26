package com.example.feature_collection.presentation.mappers

import com.example.feature_collection_api.domain.Film
import com.example.feature_collection.model.FilmLinearItem
import javax.inject.Inject

class FilmIntoLinearFilmMapper @Inject constructor() {

    fun map(model: com.example.feature_collection_api.domain.Film, onItemClicked: ((Int) -> Unit)? = null): FilmLinearItem {
        return FilmLinearItem(
            id = model.id,
            title = model.title,
            onItemClicked = onItemClicked
        )
    }
}