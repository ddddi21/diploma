package com.example.feature_collection.presentation.watchLaterFilmCollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.feature_collection.databinding.WillWatchLaterFilmsFragmentBinding
import com.example.feature_collection.di.CollectionFeatureComponent
import com.example.feature_collection.di.CollectionFeatureKey
import com.example.feature_collection.model.Film
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils

private const val GRID_LAYOUT_SPAN_COUNT = 3

class WillWatchLaterFilmsFragment : BaseFragment<WillWatchLaterFilmsViewModel>() {

    private lateinit var binding: WillWatchLaterFilmsFragmentBinding

    private val film = Film(
        id = 1,
        title = "Money Heist",
        rating = 9.7,
        posterUrl = "https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/f1c8eee6-4d0d-4808-9cec-3d1e21e4b5a0/600x900"
    )
    // временный вариант

    private var testedFilms = arrayListOf(
        film,
        film,
        film,
        film,
        film,
        film,
        film,
        film,
        film,
        film,
        film,
        film,
        film,
        film
    )
    // временный вариант

    private val filmsAdapter = WillWatchLaterFilmAdapter(testedFilms)

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
        initRecyclerView()
        swipeOnRefreshListener()
    }

    override fun subscribe(viewModel: WillWatchLaterFilmsViewModel) {
//        TODO("Not yet implemented")
    }

    private fun initRecyclerView() {
        with(binding.filmsRecyclerView) {
            adapter = filmsAdapter
            layoutManager = GridLayoutManager(context, GRID_LAYOUT_SPAN_COUNT)
        }
    }

    private fun swipeOnRefreshListener() {
        binding.swipeToRefreshFilmsList.setOnRefreshListener {
            binding.swipeToRefreshFilmsList.isRefreshing = false
            filmsAdapter.update(testedFilms)
        }
    }
    // временный вариант
}