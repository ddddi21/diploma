package com.example.feature_collection.presentation.mappers

import com.example.feature_collection.data.network.model.Film
import com.example.feature_collection.model.FilmLinearItem
import javax.inject.Inject

class FilmIntoLinearFilmMapper @Inject constructor() {

    fun map(model: Film, onItemClicked: ((Int) -> Unit)? = null): FilmLinearItem {
        return FilmLinearItem(
            id = model.id,
            title = model.title,
            onItemClicked = onItemClicked
        )
    }
}