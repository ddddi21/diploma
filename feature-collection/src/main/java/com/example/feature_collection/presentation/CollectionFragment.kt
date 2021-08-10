package com.example.feature_collection.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.feature_collection.R
import com.example.feature_collection.databinding.CollectionFragmentBinding
import com.example.feature_collection.di.CollectionFeatureComponent
import com.example.feature_collection.di.CollectionFeatureKey
import com.example.feature_collection.presentation.watchedFilmCollection.WatchedCollectionFilmsFragment
import com.example.feature_collection.presentation.watchLaterFilmCollection.WillWatchLaterFilmsFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils

class CollectionFragment : BaseFragment<CollectionViewModel>() {

    private lateinit var binding: CollectionFragmentBinding

    private lateinit var demoCollectionAdapter: DemoCollectionAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CollectionFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        demoCollectionAdapter = DemoCollectionAdapter(this)
        viewPager = binding.viewPager
        viewPager.adapter = demoCollectionAdapter
        val tabLayout = binding.tabLayout
        val tabNames: Array<String> = arrayOf(
            resources.getString(R.string.collection_tab_will_watch_tab),
                resources.getString(R.string.collection_tab_already_watched_tab)
        )
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNames[position]
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
    }

    override fun subscribe(viewModel: CollectionViewModel) {
//        TODO("Not yet implemented")
    }
}

class DemoCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WillWatchLaterFilmsFragment()
            else -> WatchedCollectionFilmsFragment()
        }
    }
}