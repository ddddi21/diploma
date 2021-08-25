package com.example.feature_collection.presentation.nestedFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_collection.R
import com.example.feature_collection.databinding.WillWatchLaterFilmsFragmentBinding
import com.example.feature_collection.di.CollectionFeatureComponent
import com.example.feature_collection.di.CollectionFeatureKey
import com.example.feature_collection.presentation.CollectionMenuItemMode
import com.example.feature_collection.presentation.CollectionViewModel
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.base.adapter.BaseAdapter
import com.technokratos.common.di.FeatureUtils
import com.technokratos.common.utils.changeVisibility
import com.technokratos.common.utils.removeItemDecorations
import com.technokratos.common.utils.setDivider

private const val GRID_LAYOUT_SPAN_COUNT = 3

private const val VIEW_PAGER_FRAGMENT_TYPE_KEY = "VIEW_PAGER_FRAGMENT_TYPE_KEY"

class WillWatchLaterFilmsFragment : BaseFragment<WillWatchLaterFilmsViewModel>() {

    companion object {
        fun getInstance(viewPagerFragmentType: ViewPagerFragmentType) = WillWatchLaterFilmsFragment().apply {
            arguments = bundleOf(VIEW_PAGER_FRAGMENT_TYPE_KEY to viewPagerFragmentType)
        }
    }

    private lateinit var binding: WillWatchLaterFilmsFragmentBinding

    private val collectionViewModel by viewModels<CollectionViewModel>({ requireParentFragment() })

    private val fragmentType by lazy {
        arguments?.getSerializable(VIEW_PAGER_FRAGMENT_TYPE_KEY) as ViewPagerFragmentType
    }

    private val linearFilmsAdapter = BaseAdapter()
    private val gridFilmsAdapter = BaseAdapter()

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
        viewModel.onViewInited(fragmentType)
        setGridLayout()
        setSwipeOnRefreshListener()
    }

    override fun subscribe(viewModel: WillWatchLaterFilmsViewModel) {
        collectionViewModel.collectionMenuItemMode.observe(viewLifecycleOwner) { itemMode ->
            when (itemMode) {
                CollectionMenuItemMode.LINEAR_LIST -> setLinearLayout()

                else -> setGridLayout()
            }
        }
        viewModel.gridList.observe(viewLifecycleOwner) { list ->
            gridFilmsAdapter.update(list)
        }

        viewModel.linearList.observe(viewLifecycleOwner) { list ->
            linearFilmsAdapter.update(list)
            binding.filmsRecyclerView.changeVisibility(list.isNotEmpty())
            binding.noFilmTextView.changeVisibility(list.isEmpty())
        }
    }

    private fun setGridLayout() = with(binding.filmsRecyclerView) {
        layoutManager = GridLayoutManager(context, GRID_LAYOUT_SPAN_COUNT)
        adapter = gridFilmsAdapter
        removeItemDecorations()
    }

    private fun setLinearLayout() = with(binding.filmsRecyclerView) {
        layoutManager = LinearLayoutManager(context)
        adapter = linearFilmsAdapter
        setDivider(R.drawable.film_list_item_divider)
    }

    private fun setSwipeOnRefreshListener() {
        binding.swipeToRefreshFilmsList.setOnRefreshListener {
            binding.swipeToRefreshFilmsList.isRefreshing = false
            viewModel.onRefreshSwiped()
        }
    }
    // временный вариант
}

enum class ViewPagerFragmentType {
    WILL_WATCH,
    WATCHED
}