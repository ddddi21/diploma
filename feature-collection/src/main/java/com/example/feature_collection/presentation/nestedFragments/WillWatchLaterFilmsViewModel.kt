package com.example.feature_collection.presentation.nestedFragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.feature_collection.CollectionRouter
import com.example.feature_collection.model.FilmGridItem
import com.example.feature_collection.model.FilmLinearItem
import com.technokratos.common.base.BaseViewModel
import com.technokratos.common.base.adapter.ViewType

class WillWatchLaterFilmsViewModel(
    private var router: CollectionRouter
) : BaseViewModel() {

    private var _gridList = MutableLiveData<List<ViewType>>()
    val gridList: LiveData<List<ViewType>> = _gridList

    private var _linearList = MutableLiveData<List<ViewType>>()
    val linearList: LiveData<List<ViewType>> = _linearList

    private val _fragmentType = MutableLiveData<ViewPagerFragmentType>()

    private val filmWatched = FilmGridItem(
        id = 1,
        title = "Money Heist",
        rating = 9.7,
        posterUrl = "https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/f1c8eee6-4d0d-4808-9cec-3d1e21e4b5a0/600x900"
    )
    // временный вариант

    private val filmMiniWatched = FilmLinearItem(
        id = 1,
        title = "Money Heist"
    )
    // временный вариант

    private val filmWillWatch = FilmGridItem(
        id = 0,
        title = "Peaky Blinders",
        rating = 9.9,
        posterUrl = "https://upload.wikimedia.org/wikipedia/ru/thumb/6/6e/%D0%9E%D1%81%D1%82%D1%80%D0%9A%D0%BE%D0%B7.jpg/274px-%D0%9E%D1%81%D1%82%D1%80%D0%9A%D0%BE%D0%B7.jpg"
    )
    // временный вариант

    private val filmMiniWillWatch = FilmLinearItem(
        id = 0,
        title = "Peaky Blinders"
    )
    // временный вариант

    private var testedMiniFilmsWatched = List(10) { filmMiniWatched } // временный вариант
    private var testedFilmsWatched = List(10) { filmWatched } // временный вариант

    private var testedMiniFilmsWillWatch = List(10) { filmMiniWillWatch } // временный вариант
    private var testedFilmsWillWatch = List(10) { filmWillWatch } // временный вариант

    fun onViewInited(fragmentType: ViewPagerFragmentType) {
        _fragmentType.value = fragmentType
        loadList()
    }

    private fun loadList() {
        // TODO
        when (_fragmentType.value) {
            ViewPagerFragmentType.WATCHED -> {
                _gridList.value = testedFilmsWatched
                _linearList.value = testedMiniFilmsWatched
            }

            ViewPagerFragmentType.WILL_WATCH -> {
                _gridList.value = testedFilmsWillWatch
                _linearList.value = testedMiniFilmsWillWatch
            }
        }
    }

    fun onRefreshSwiped() {
        // TODO
        loadList()
    }
}