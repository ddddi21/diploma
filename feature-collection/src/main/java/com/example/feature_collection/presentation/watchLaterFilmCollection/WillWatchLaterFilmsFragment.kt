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

    private val filmsAdapter = BaseAdapter()

    private var testedMiniFilms = List(10) { filmMini }
    // временный вариант

    private var testedFilms = List(10) { film } // временный вариант

    private val miniFilmsAdapter = BaseAdapter(testedMiniFilms)

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
        initRecyclerView(GridLayoutManager(context, GRID_LAYOUT_SPAN_COUNT), filmsAdapter)
        filmsAdapter.update(testedFilms)
        setUpRecyclerViewWithoutPoster()
        setSwipeOnRefreshListener()
    }

    override fun subscribe(viewModel: WillWatchLaterFilmsViewModel) {
//        TODO("Not yet implemented")
    }

    private fun initRecyclerView(requiredLayoutManager: RecyclerView.LayoutManager, requiredAdapter: BaseAdapter) {
        with(binding.filmsRecyclerView) {
            adapter = requiredAdapter
            layoutManager = requiredLayoutManager
            if (requiredAdapter == miniFilmsAdapter) {
                setDivider(R.drawable.film_list_item_divider)
            } else {
                removeItemDecorations()
            }
        }
    }

    private fun setUpRecyclerViewWithoutPoster() {
        viewModelParent.isNeedToChangeList.observe(viewLifecycleOwner) { isNeedToSwitchToGridLayout ->
            when (isNeedToSwitchToGridLayout) {
                false -> initRecyclerView(LinearLayoutManager(context), miniFilmsAdapter)
                true -> initRecyclerView(GridLayoutManager(context, GRID_LAYOUT_SPAN_COUNT), filmsAdapter)
            }
        }
    }

    private fun setSwipeOnRefreshListener() {
        binding.swipeToRefreshFilmsList.setOnRefreshListener {
            binding.swipeToRefreshFilmsList.isRefreshing = false
            filmsAdapter.update(testedFilms)
        }
    }
    // временный вариант
}