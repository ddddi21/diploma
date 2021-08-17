package com.example.feature_film_details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.feature_film_details.databinding.FilmDetailsFragmentBinding
import com.example.feature_film_details.di.FilmDetailsFeatureComponent
import com.example.feature_film_details.di.FilmDetailsFeatureKey
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils
import com.technokratos.common.utils.setChip

class FilmDetailsFragment : BaseFragment<FilmDetailsViewModel>() {

    private lateinit var binding: FilmDetailsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FilmDetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun inject() {
        FeatureUtils.getFeature<FilmDetailsFeatureComponent>(this, FilmDetailsFeatureKey::class.java)
            .filmDetailsComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
        setChips()
        binding.addToWillWatchButton.setOnClickListener { addToWillWatchButton ->
            addToWillWatchButton.visibility = View.GONE
            binding.watchedChipGroup.visibility = View.VISIBLE
        }

        with(binding.collectionToolbar) {
            setNavigationOnClickListener {
                viewModel.onBackToCollectionScreenClicked()
            }
        }
    }

    override fun subscribe(viewModel: FilmDetailsViewModel) {
//        TODO("Not yet implemented")
    }

    private fun setChips() {
        binding.genreChipGroup.setChip("Test") // будем брать текст из вью модели
        binding.genreChipGroup.setChip("Testttt")
        binding.genreChipGroup.setChip("Testklmbklml")
        binding.genreChipGroup.setChip("Testggg")
        binding.genreChipGroup.setChip("Test")
        binding.genreChipGroup.setChip("Test")
        binding.genreChipGroup.setChip("Test")
    } // временный вариант
}