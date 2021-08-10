package com.example.feature_collection.model

import com.example.feature_collection.R
import com.technokratos.common.base.adapter.ViewType

data class Film(
    val id: Int,
    val title: String,
    val rating: Double,
    val posterUrl: String,
    val onItemClicked: ((Int) -> Unit) ? = null
) : ViewType(R.layout.film_item_view)