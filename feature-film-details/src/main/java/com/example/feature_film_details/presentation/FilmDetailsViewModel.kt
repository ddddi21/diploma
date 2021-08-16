package com.example.feature_film_details.presentation

import com.example.feature_film_details.FilmDetailsRouter
import com.technokratos.common.base.BaseViewModel
import com.technokratos.common.router.NavigateBackRouter

class FilmDetailsViewModel(
    private var router: FilmDetailsRouter,
    private val navigateBackRouter: NavigateBackRouter
) : BaseViewModel() {

    fun onBackToCollectionScreenClicked() {
        navigateBackRouter.goToPreviousScreen()
    }
}