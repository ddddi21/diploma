package com.example.feature_film_details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
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

private const val FILM_KEY = "FILM_KEY"

class FilmDetailsFragment : BaseFragment<FilmDetailsViewModel>() {

    companion object {
        fun buildArgs(film: Film) = bundleOf(FILM_KEY to film)
    }

    private val film by lazy {
        arguments?.getSerializable(FILM_KEY) as Film
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
        viewModel.setFilm(film)
        viewModel.initChipsState(film.status)

        setFilm()
        binding.addToWillWatchButton.setOnClickListener {
            makeWatchedChipGroupVisible()
        }
        binding.willWatchSelectedChip.setOnClickListener {
            viewModel.onWillWatchChipClicked()
        }
        binding.watchedSelectedChip.setOnClickListener {
            viewModel.onWatchedChipClicked()
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
}