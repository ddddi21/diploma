package com.example.feature_collection.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.feature_collection.R
import com.example.feature_collection.databinding.CollectionFragmentBinding
import com.example.feature_collection.di.CollectionFeatureComponent
import com.example.feature_collection.di.CollectionFeatureKey
import com.example.feature_collection.presentation.watchLaterFilmCollection.WillWatchLaterFilmsFragment
import com.example.feature_collection.presentation.watchedFilmCollection.WatchedCollectionFilmsFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils

class CollectionFragment : BaseFragment<CollectionViewModel>() {

    private lateinit var binding: CollectionFragmentBinding

    private lateinit var menu: MenuItem

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CollectionFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val collectionList = listOf(
            resources.getString(R.string.collection_will_watch_tab) to WillWatchLaterFilmsFragment(),
            resources.getString(R.string.collection_already_watched_tab) to WatchedCollectionFilmsFragment()
        )
        binding.viewPager.adapter = CollectionAdapter(
            collectionList = collectionList,
            fragment = this
        )
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = collectionList[position].first
        }.attach()
    }

    override fun inject() {
        FeatureUtils.getFeature<CollectionFeatureComponent>(this, CollectionFeatureKey::class.java)
            .collectionComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
        setHasOptionsMenu(true)
        menu = binding.collectionToolbar.menu.findItem(R.id.list)
        menu.setOnMenuItemClickListener {
            when (viewModel.isNeedToChangeList.value) {
                true -> changeListToLinearLayout()
                false -> changeListToGridLayout()
            }
            false
        }
    }

    override fun subscribe(viewModel: CollectionViewModel) {
//        TODO("Not yet implemented")
    }

    private fun changeListToLinearLayout() {
        viewModel.onMiniListClicked()
        menu.icon = resources.getDrawable(R.drawable.ic_full_film_list)
    }

    private fun changeListToGridLayout() {
        viewModel.onFullListClicked()
        menu.icon = resources.getDrawable(R.drawable.ic_mini_films_list)
    }
}

class CollectionAdapter(
    private val collectionList: List<Pair<String, Fragment>>,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = collectionList.size

    override fun createFragment(position: Int) = collectionList[position].second
}