package com.example.feature_collection.presentation.nestedFragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.feature_collection.CollectionRouter
import com.example.feature_collection.data.network.model.Film
import com.example.feature_collection.domain.CollectionInteractor
import com.example.feature_collection.model.FilmGridItem
import com.example.feature_collection.model.FilmLinearItem
import com.technokratos.common.base.BaseViewModel
import com.technokratos.common.base.adapter.ViewType
import kotlinx.coroutines.launch

private const val DEFAULT_POSTER_URL = "https://upload.wikimedia.org/wikipedia/ru/thumb/6/6e/%D0%9E%D1%81%D1%82%D1%80%D0%9A%D0%BE%D0%B7.jpg/274px-%D0%9E%D1%81%D1%82%D1%80%D0%9A%D0%BE%D0%B7.jpg"

class WillWatchLaterFilmsViewModel(
    private val router: CollectionRouter,
    private val interactor: CollectionInteractor
) : BaseViewModel() {

    private var _gridList = MutableLiveData<List<ViewType>>()
    val gridList: LiveData<List<ViewType>> = _gridList

    private var _linearList = MutableLiveData<List<ViewType>>()
    val linearList: LiveData<List<ViewType>> = _linearList

    private var fragmentType = ViewPagerFragmentType.WILL_WATCH

    fun onViewInited(fragmentType: ViewPagerFragmentType) {
        this.fragmentType = fragmentType
        loadList()
    }

    private fun loadList() {
        viewModelScope.launch {
            when (fragmentType) {
                ViewPagerFragmentType.WATCHED -> {
                    interactor.getUserFilms(true).onSuccess { filmList ->
                        _gridList.value = filmList.map { item ->
                            mapIntoGridFilm(item)
                        }
                        _linearList.value = filmList.map { item ->
                            mapIntoLinearFilm(item)
                        }
                    }
                }

                ViewPagerFragmentType.WILL_WATCH -> {
                    interactor.getUserFilms(false).onSuccess { filmList ->
                        _gridList.value = filmList.map { item ->
                            mapIntoGridFilm(item)
                        }
                        _linearList.value = filmList.map { item ->
                            mapIntoLinearFilm(item)
                        }
                    }
                }
            }
        }
    }

    fun onRefreshSwiped() {
        // TODO
        loadList()
    }

    private fun mapIntoGridFilm(model: Film): FilmGridItem {
        return FilmGridItem(
            id = model.id,
            title = model.title,
            description = model.description ?: "",
            genres = model.genres ?: listOf(""),
            rating = model.rating ?: 0.0,
            posterUrl = model.posterUrl ?: DEFAULT_POSTER_URL,
            onItemClicked = { router.navigateToFilmDetailsScreen() }
        )
    }

    private fun mapIntoLinearFilm(model: Film): FilmLinearItem {
        return FilmLinearItem(
            id = model.id,
            title = model.title,
            onItemClicked = { router.navigateToFilmDetailsScreen() }
        )
    }
}