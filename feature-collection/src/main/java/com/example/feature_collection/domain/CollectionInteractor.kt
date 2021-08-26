package com.example.feature_collection.domain

import com.example.feature_collection.data.network.model.Film
import com.example.feature_collection.presentation.nestedFragments.ViewPagerFragmentType
import javax.inject.Inject

class CollectionInteractor @Inject constructor(
    private val collectionRepository: CollectionRepository
) {

    suspend fun getUserFilms(fragmentType: ViewPagerFragmentType): Result<List<Film>> = runCatching {
        when (fragmentType) {
            ViewPagerFragmentType.WATCHED -> collectionRepository.getUserFilms(true)
            ViewPagerFragmentType.WILL_WATCH -> collectionRepository.getUserFilms(false)
        }
    }
}