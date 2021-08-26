package com.example.feature_film_details.presentation

import androidx.lifecycle.viewModelScope
import com.example.feature_film_details.FilmDetailsRouter
import com.example.feature_film_details.domain.FilmDetailsInteractor
import com.technokratos.common.base.BaseViewModel
import kotlinx.coroutines.launch

class FilmDetailsViewModel(
    private var router: FilmDetailsRouter,
    private val interactor: FilmDetailsInteractor
) : BaseViewModel() {

    fun onBackToCollectionScreenClicked() {
        router.goToPreviousScreen()
    }

    fun onFilmCollectionAdded(filmId: Int) {
        viewModelScope.launch {
            interactor.markFilmAsWatched(filmId)
        }
    }
}