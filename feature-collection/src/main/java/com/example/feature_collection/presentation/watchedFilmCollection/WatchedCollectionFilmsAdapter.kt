package com.example.feature_collection.presentation.watchedFilmCollection

import com.example.feature_collection.model.Film
import com.technokratos.common.base.adapter.BaseAdapter

class WatchedCollectionFilmsAdapter(
    private val films: List<Film>
) : BaseAdapter(films)