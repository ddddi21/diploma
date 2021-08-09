package com.example.feature_collection.presentation.watchLater

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.feature_collection.databinding.WillWatchLaterFilmsFragmentBinding
import com.example.feature_collection.di.CollectionFeatureComponent
import com.example.feature_collection.di.CollectionFeatureKey
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils

class WillWatchLaterFilmsFragment : BaseFragment<WillWatchLaterFilmsViewModel>() {

    private lateinit var binding: WillWatchLaterFilmsFragmentBinding

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
//        TODO("Not yet implemented")
    }

    override fun subscribe(viewModel: WillWatchLaterFilmsViewModel) {
//        TODO("Not yet implemented")
    }
}