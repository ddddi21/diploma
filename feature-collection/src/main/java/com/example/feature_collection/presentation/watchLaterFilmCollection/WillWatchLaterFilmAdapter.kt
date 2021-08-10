package com.example.feature_collection.presentation.watchLaterFilmCollection

import com.example.feature_collection.model.Film
import com.technokratos.common.base.adapter.BaseAdapter
import com.technokratos.common.base.adapter.ViewType

class WillWatchLaterFilmAdapter(
    private val films: List<Film>,
    entities: List<ViewType> = emptyList()
) : BaseAdapter(entities)