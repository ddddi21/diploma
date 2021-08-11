package com.example.feature_collection.presentation.watchLaterFilmCollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_collection.R
import com.example.feature_collection.databinding.WillWatchLaterFilmsFragmentBinding
import com.example.feature_collection.di.CollectionFeatureComponent
import com.example.feature_collection.di.CollectionFeatureKey
import com.example.feature_collection.model.Film
import com.example.feature_collection.model.FilmMini
import com.example.feature_collection.presentation.CollectionViewModel
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.base.adapter.BaseAdapter
import com.technokratos.common.base.adapter.ViewType
import com.technokratos.common.di.FeatureUtils
import com.technokratos.common.utils.removeItemDecorations
import com.technokratos.common.utils.setDivider

private const val GRID_LAYOUT_SPAN_COUNT = 3

class WillWatchLaterFilmsFragment : BaseFragment<WillWatchLaterFilmsViewModel>() {

    private lateinit var binding: WillWatchLaterFilmsFragmentBinding

    private val viewModelParent by viewModels<CollectionViewModel>({ requireParentFragment() })

    private val film = Film(
        id = 1,
        title = "Money Heist",
        rating = 9.7,
        posterUrl = "https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/f1c8eee6-4d0d-4808-9cec-3d1e21e4b5a0/600x900"
    )
    // временный вариант

    private val filmMini = FilmMini(
        id = 1,
        title = "Money Heist"
    )
    // временный вариант

    private var testedMiniFilms = List(10) { filmMini } // временный вариант
    private var testedFilms = List(10) { film } // временный вариант

    private val filmsAdapter = BaseAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = WillWatchLaterFilmsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun inject() {
        FeatureUtils.getFeature<CollectionFeatureComponent>(this, CollectionFeatureKey::class.java)
            .willWatchLaterFilmsFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
        initRecyclerView(GridLayoutManager(context, GRID_LAYOUT_SPAN_COUNT), testedFilms)
        setUpRecyclerViewWithoutPoster()
    }

    override fun subscribe(viewModel: WillWatchLaterFilmsViewModel) {
//        TODO("Not yet implemented")
    }

    private fun initRecyclerView(requiredLayoutManager: RecyclerView.LayoutManager, list: List<ViewType>) {
        with(binding.filmsRecyclerView) {
            layoutManager = requiredLayoutManager
            if (list == testedMiniFilms) {
                setSwipeOnRefreshListener(testedMiniFilms)
                filmsAdapter.update(testedMiniFilms)
                setDivider(R.drawable.film_list_item_divider)
            } else {
                setSwipeOnRefreshListener(testedFilms)
                removeItemDecorations()
                filmsAdapter.update(testedFilms)
            }
            adapter = filmsAdapter
        }
    }

    private fun setUpRecyclerViewWithoutPoster() {
        viewModelParent.isNeedToChangeList.observe(viewLifecycleOwner) { isNeedToSwitchToGridLayout ->
            when (isNeedToSwitchToGridLayout) {
                false -> initRecyclerView(LinearLayoutManager(context), testedMiniFilms)
                true -> initRecyclerView(GridLayoutManager(context, GRID_LAYOUT_SPAN_COUNT), testedFilms)
            }
        }
    }

    private fun setSwipeOnRefreshListener(list: List<ViewType>) {
        binding.swipeToRefreshFilmsList.setOnRefreshListener {
            binding.swipeToRefreshFilmsList.isRefreshing = false
            when (list) {
                testedFilms -> filmsAdapter.update(testedFilms)
                testedMiniFilms -> filmsAdapter.update(testedMiniFilms)
            }
        }
    }
    // временный вариант
}