package com.example.feature_film_details.presentation

import androidx.lifecycle.viewModelScope
import com.example.feature_collection_api.domain.model.Film
import com.example.feature_collection_api.domain.model.ViewPagerFragmentType
import com.example.feature_film_details.FilmDetailsRouter
import com.example.feature_film_details.domain.FilmDetailsInteractor
import com.technokratos.common.base.BaseViewModel
import kotlinx.coroutines.launch

class FilmDetailsViewModel(
    private var router: FilmDetailsRouter,
    private val interactor: FilmDetailsInteractor
) : BaseViewModel() {

    private var isWillWatchChipChecked = false
    private var isWatchedChipChecked = false

    private lateinit var film: Film

    fun setFilm(film: Film) {
        this.film = film
    }

    fun initChipsState(fragmentType: ViewPagerFragmentType?) {
        when (fragmentType) {
            ViewPagerFragmentType.WILL_WATCH -> isWillWatchChipChecked = true

            ViewPagerFragmentType.WATCHED -> isWatchedChipChecked = true
        }
    }

    fun onBackToCollectionScreenClicked() {
        router.goToPreviousScreen()
    }

    fun onWillWatchChipClicked() {
        if (isWillWatchChipChecked) return
        isWillWatchChipChecked = true
        isWatchedChipChecked = false
        markFilmAsWatched()
    }

    fun onWatchedChipClicked() {
        if (isWatchedChipChecked) return
        isWatchedChipChecked = true
        isWillWatchChipChecked = false
        markFilmAsWatched()
    }

    private fun markFilmAsWatched() {
        viewModelScope.launch {
            interactor.markFilmAsWatched(film.id)
        }
    }
}