package com.example.feature_film_details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.feature_collection_api.domain.model.Film
import com.example.feature_collection_api.domain.model.ViewPagerFragmentType
import com.example.feature_film_details.R
import com.example.feature_film_details.databinding.FilmDetailsFragmentBinding
import com.example.feature_film_details.di.FilmDetailsFeatureComponent
import com.example.feature_film_details.di.FilmDetailsFeatureKey
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils
import com.technokratos.common.utils.makeGone
import com.technokratos.common.utils.makeVisible
import com.technokratos.common.utils.setChip

private const val FILM_INSTANCE = "FILM_INSTANCE"

class FilmDetailsFragment : BaseFragment<FilmDetailsViewModel>() {

    private val film by lazy {
        arguments?.getSerializable(FILM_INSTANCE) as Film
    }

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
        setFilm()
        addFilmToCollection()
        binding.addToWillWatchButton.setOnClickListener {
            makeWatchedChipGroupVisible()
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

    private fun setFilm() = with(binding) {
        val option = RequestOptions()
            .fallback(R.drawable.ic_film_poster_template)
            .placeholder(R.drawable.ic_film_poster_template)
        Glide.with(requireContext())
            .load(film.posterUrl)
            .apply(option)
            .into(posterImageView)
        titleTextView.text = film.title
        rateTextView.text = film.rating.toString()
        film.genres?.forEach { genre ->
            genreChipGroup.setChip(genre)
        }
        filmDescriptionTextView.text = film.description
        if (film.status == ViewPagerFragmentType.WATCHED) {
            makeWatchedChipGroupVisible()
            binding.watchedSelectedChip.isChecked = true
        }
    }

    private fun makeWatchedChipGroupVisible() {
        binding.addToWillWatchButton.makeGone()
        binding.watchedChipGroup.makeVisible()
    }

    private fun addFilmToCollection() {
        if (!binding.watchedSelectedChip.isChecked) {
            binding.watchedSelectedChip.setOnClickListener {
                viewModel.onFilmCollectionAdded(film.id)
            }
        }
        if (!binding.willWatchSelectedChip.isChecked) {
            binding.willWatchSelectedChip.setOnClickListener {
                viewModel.onFilmCollectionAdded(film.id)
            }
        }
    }
}