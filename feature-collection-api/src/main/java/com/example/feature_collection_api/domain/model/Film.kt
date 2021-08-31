package com.example.feature_collection_api.domain.model

import java.io.Serializable

data class Film(
    val id: Int,
    val title: String,
    val rating: Double? = null,
    val posterUrl: String? = null,
    val description: String? = null,
    val genres: List<String>? = null,
    var status: ViewPagerFragmentType? = ViewPagerFragmentType.WILL_WATCH
) : Serializable

enum class ViewPagerFragmentType {
    WILL_WATCH,
    WATCHED
}