package com.example.feature_collection_api.domain

data class Film(
    val id: Int,
    val title: String,
    val rating: Double? = null,
    val posterUrl: String? = null,
    val description: String? = null,
    val genres: List<String>? = null
)