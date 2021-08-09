package com.example.feature_collection.presentation.filmCollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.feature_collection.databinding.FilmCollectionListFragmentBinding

class CollectionFilmListFragment : Fragment() {

    private lateinit var binding: FilmCollectionListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FilmCollectionListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}
// доделаю потом с вью моделями и di