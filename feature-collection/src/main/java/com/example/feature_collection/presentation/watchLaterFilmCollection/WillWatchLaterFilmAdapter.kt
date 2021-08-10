package com.example.feature_collection.presentation.watchLaterFilmCollection

import com.example.feature_collection.model.Film
import com.technokratos.common.base.adapter.BaseAdapter

class WillWatchLaterFilmAdapter(
    private val films: List<Film>
) : BaseAdapter(films)