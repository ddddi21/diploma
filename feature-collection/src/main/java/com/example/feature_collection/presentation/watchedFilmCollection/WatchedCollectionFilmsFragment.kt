package com.example.feature_collection.presentation.watchedFilmCollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.feature_collection.databinding.WatchedFilmCollectionFragmentBinding
import com.example.feature_collection.di.CollectionFeatureComponent
import com.example.feature_collection.di.CollectionFeatureKey
import com.example.feature_collection.model.Film
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils

private const val GRID_LAYOUT_SPAN_COUNT = 3

class WatchedCollectionFilmsFragment : BaseFragment<WatchedCollectionFilmsViewModel>() {

    private lateinit var binding: WatchedFilmCollectionFragmentBinding

    private val film: Film = Film(
        id = 0,
        title = "Movie",
        rating = 9.9,
        poster = "url"
    )

    private var testedFilms: List<Film> = arrayListOf(
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

    private val filmsAdapter: WatchedCollectionFilmsAdapter = WatchedCollectionFilmsAdapter(testedFilms)

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
        val gridLayoutManager = GridLayoutManager(context, GRID_LAYOUT_SPAN_COUNT)
        with(binding.filmsRecyclerView) {
            adapter = filmsAdapter
            layoutManager = gridLayoutManager
        }
        filmsAdapter.update(testedFilms)
    }

    override fun subscribe(viewModel: WatchedCollectionFilmsViewModel) {
//        TODO("Not yet implemented")
    }
}