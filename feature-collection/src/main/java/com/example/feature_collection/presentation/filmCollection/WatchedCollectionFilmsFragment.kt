package com.example.feature_collection.presentation.filmCollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.feature_collection.databinding.WatchedFilmCollectionFragmentBinding
import com.example.feature_collection.di.CollectionFeatureComponent
import com.example.feature_collection.di.CollectionFeatureKey
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils

class WatchedCollectionFilmsFragment : BaseFragment<WatchedCollectionFilmsViewModel>() {

    private lateinit var binding: WatchedFilmCollectionFragmentBinding

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
//        TODO("Not yet implemented")
    }

    override fun subscribe(viewModel: WatchedCollectionFilmsViewModel) {
//        TODO("Not yet implemented")
    }
}