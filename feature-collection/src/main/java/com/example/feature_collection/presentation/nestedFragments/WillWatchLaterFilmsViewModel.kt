package com.example.feature_collection.presentation.nestedFragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.feature_collection.CollectionRouter
import com.example.feature_collection.domain.CollectionInteractor
import com.example.feature_collection.presentation.mappers.FilmIntoPresentationFilmMapper
import com.technokratos.common.base.BaseViewModel
import com.technokratos.common.base.adapter.ViewType
import kotlinx.coroutines.launch

class WillWatchLaterFilmsViewModel(
    private val router: CollectionRouter,
    private val interactor: CollectionInteractor,
    private val filmIntoPresentationFilmMapper: FilmIntoPresentationFilmMapper
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
            interactor.getUserFilms(fragmentType).onSuccess { filmList ->
                _gridList.value = filmList.map { item ->
                    filmIntoPresentationFilmMapper.mapIntoGridFilm(item) { router.navigateToFilmDetailsScreen() }
                }
                _linearList.value = filmList.map { item ->
                    filmIntoPresentationFilmMapper.mapIntoLinearFilm(item) { router.navigateToFilmDetailsScreen() }
                }
            }
        }
    }

    fun onRefreshSwiped() {
        loadList()
    }
}