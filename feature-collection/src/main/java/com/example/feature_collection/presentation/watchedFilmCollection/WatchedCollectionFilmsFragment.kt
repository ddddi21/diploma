package com.example.feature_collection.presentation.watchedFilmCollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.technokratos.common.base.adapter.BaseAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_collection.R
import com.example.feature_collection.databinding.WatchedFilmCollectionFragmentBinding
import com.example.feature_collection.di.CollectionFeatureComponent
import com.example.feature_collection.di.CollectionFeatureKey
import com.example.feature_collection.model.Film
import com.example.feature_collection.model.FilmMini
import com.example.feature_collection.presentation.CollectionViewModel
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.base.adapter.ViewType
import com.technokratos.common.di.FeatureUtils
import com.technokratos.common.utils.removeItemDecorations
import com.technokratos.common.utils.setDivider

private const val GRID_LAYOUT_SPAN_COUNT = 3

class WatchedCollectionFilmsFragment : BaseFragment<WatchedCollectionFilmsViewModel>() {

    private lateinit var binding: WatchedFilmCollectionFragmentBinding

    private val viewModelParent by viewModels<CollectionViewModel> ({ requireParentFragment() })

    private val film = Film(
        id = 0,
        title = "Peaky Blinders",
        rating = 9.9,
        posterUrl = "https://upload.wikimedia.org/wikipedia/ru/thumb/6/6e/%D0%9E%D1%81%D1%82%D1%80%D0%9A%D0%BE%D0%B7.jpg/274px-%D0%9E%D1%81%D1%82%D1%80%D0%9A%D0%BE%D0%B7.jpg"
    )
    // временный вариант

    private val filmMini = FilmMini(
        id = 0,
        title = "Peaky Blinders"
    )
    // временный вариант

    private var testedFilms = List(10) { film }
    private var testedMiniFilms = List(10) { filmMini } // временный вариант

    private val filmsAdapter = BaseAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = WatchedFilmCollectionFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun inject() {
        FeatureUtils.getFeature<CollectionFeatureComponent>(this, CollectionFeatureKey::class.java)
            .watchedCollectionFilmsFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
        initRecyclerView(GridLayoutManager(context, GRID_LAYOUT_SPAN_COUNT), testedFilms)
        setUpRecyclerViewWithoutPoster()
    }

    override fun subscribe(viewModel: WatchedCollectionFilmsViewModel) {
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
                filmsAdapter.update(testedFilms)
                removeItemDecorations()
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