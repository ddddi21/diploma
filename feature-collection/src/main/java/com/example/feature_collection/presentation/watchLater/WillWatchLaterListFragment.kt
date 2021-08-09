package com.example.feature_collection.presentation.watchLater

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.feature_collection.databinding.WillWatchFilmListFragmentBinding

class WillWatchLaterListFragment : Fragment() {

    private lateinit var binding: WillWatchFilmListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = WillWatchFilmListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}
// доделаю потом с вью моделями и di