package com.example.feature_collection.presentation.watchLater

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.feature_collection.databinding.WillWatchLaterFilmsFragmentBinding

class WillWatchLaterFilmsFragment : Fragment() {

    private lateinit var binding: WillWatchLaterFilmsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = WillWatchLaterFilmsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}
// доделаю потом с вью моделями и di