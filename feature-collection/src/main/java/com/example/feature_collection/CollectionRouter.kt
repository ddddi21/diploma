package com.example.feature_collection

import com.example.feature_collection_api.domain.model.Film

interface CollectionRouter {

    fun navigateToFilmDetailsScreen(film: Film)
}