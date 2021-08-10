package com.example.feature_collection.presentation.watchedFilmCollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.technokratos.common.base.adapter.BaseAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_collection.databinding.WatchedFilmCollectionFragmentBinding
import com.example.feature_collection.di.CollectionFeatureComponent
import com.example.feature_collection.di.CollectionFeatureKey
import com.example.feature_collection.model.Film
import com.example.feature_collection.model.FilmMini
import com.example.feature_collection.presentation.CollectionViewModel
import com.example.feature_collection.presentation.watchedFilmCollection.adapter.WatchedCollectionFilmsAdapter
import com.example.feature_collection.presentation.watchedFilmCollection.adapter.WatchedCollectionFilmsMiniListAdapter
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils

private const val GRID_LAYOUT_SPAN_COUNT = 3

class WatchedCollectionFilmsFragment : BaseFragment<WatchedCollectionFilmsViewModel>() {

    private lateinit var binding: WatchedFilmCollectionFragmentBinding

    private val viewModelParent by viewModels<CollectionViewModel> ({ requireParentFragment() })

    private val film: Film = Film(
        id = 0,
        title = "Peaky Blinders",
        rating = 9.9,
        posterUrl = "https://upload.wikimedia.org/wikipedia/ru/thumb/6/6e/%D0%9E%D1%81%D1%82%D1%80%D0%9A%D0%BE%D0%B7.jpg/274px-%D0%9E%D1%81%D1%82%D1%80%D0%9A%D0%BE%D0%B7.jpg"
    )
    // временный вариант

    private val filmMini: FilmMini = FilmMini(
        id = 0,
        title = "Peaky Blinders"
    )
    // временный вариант

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

    private var testedMiniFilms = arrayListOf(
        filmMini,
        filmMini,
        filmMini,
        filmMini,
        filmMini
    )
    // временный вариант

    private val filmsAdapter: WatchedCollectionFilmsAdapter = WatchedCollectionFilmsAdapter(testedFilms)

    private val miniFilmsAdapter: WatchedCollectionFilmsMiniListAdapter = WatchedCollectionFilmsMiniListAdapter(testedMiniFilms)

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
        initRecyclerView(GridLayoutManager(context, GRID_LAYOUT_SPAN_COUNT), filmsAdapter)
        filmsAdapter.update(testedFilms) // знаю, что должно быть во вью модели. пока тестовый вариант, все равно нет обращения в сеть
        swipeOnRefreshListener()
        setUpRecyclerViewWithoutPoster()
    }

    override fun subscribe(viewModel: WatchedCollectionFilmsViewModel) {
//        TODO("Not yet implemented")
    }

    private fun initRecyclerView(requiredLayoutManager: RecyclerView.LayoutManager, requiredAdapter: BaseAdapter) {
        with(binding.filmsRecyclerView) {
            adapter = requiredAdapter
            layoutManager = requiredLayoutManager
        }
    }

    private fun setUpRecyclerViewWithoutPoster() {
        if (viewModelParent.isMiniListClicked.value == true) {
            initRecyclerView(LinearLayoutManager(context), miniFilmsAdapter)
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