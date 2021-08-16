package com.example.feature_collection.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.feature_collection.R
import com.example.feature_collection.databinding.CollectionFragmentBinding
import com.example.feature_collection.di.CollectionFeatureComponent
import com.example.feature_collection.di.CollectionFeatureKey
import com.example.feature_collection.presentation.nestedFragments.ViewPagerFragmentType
import com.example.feature_collection.presentation.nestedFragments.WillWatchLaterFilmsFragment
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
        val fragmentWillWatch = WillWatchLaterFilmsFragment.getInstance(ViewPagerFragmentType.WILL_WATCH)
        val fragmentWatched = WillWatchLaterFilmsFragment.getInstance(ViewPagerFragmentType.WATCHED)
        val collectionList = listOf(
            resources.getString(R.string.collection_will_watch_tab) to fragmentWillWatch,
            resources.getString(R.string.collection_already_watched_tab) to fragmentWatched
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
            viewModel.onMenuItemClicked()
            false
        }
    }

    override fun subscribe(viewModel: CollectionViewModel) {
        viewModel.collectionMenuItemMode.observe(viewLifecycleOwner) { listMode ->
            when (listMode) {
                CollectionMenuItemMode.GRID_LIST -> {
                    menu.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_mini_films_list)
                }

                CollectionMenuItemMode.LINEAR_LIST -> {
                    menu.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_full_film_list)
                }
            }
        }
    }
}

class CollectionAdapter(
    private val collectionList: List<Pair<String, Fragment>>,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = collectionList.size

    override fun createFragment(position: Int) = collectionList[position].second
}