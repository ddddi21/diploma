package com.example.feature_film_details.presentation

import com.example.feature_film_details.FilmDetailsRouter
import com.technokratos.common.base.BaseViewModel

class FilmDetailsViewModel(
    private var router: FilmDetailsRouter
) : BaseViewModel() {

    fun onBackToCollectionScreenClicked() {
        router.goToPreviousScreen()
    }
}