package com.example.feature_collection.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.feature_collection.databinding.CollectionFragmentBinding
import com.technokratos.common.base.BaseFragment

class CollectionFragment : BaseFragment<CollectionViewModel> () {

    private lateinit var binding: CollectionFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CollectionFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun inject() {
//        TODO("Not yet implemented")
    }

    override fun initViews() {
//        TODO("Not yet implemented")
    }

    override fun subscribe(viewModel: CollectionViewModel) {
//        TODO("Not yet implemented")
    }
}