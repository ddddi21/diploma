package com.example.navigation.router

import android.os.Bundle
import com.example.feature_collection_api.domain.model.Film
import com.example.feature_film_details.presentation.FilmDetailsFragment
import com.technokratos.splash.R
import com.example.navigation.navigation.NavControllerProvider
import javax.inject.Inject

class RouterImpl @Inject constructor(
    private val navControllerProvider: NavControllerProvider
) : Router {

    override fun toLogin() {
        navigateTo(R.id.action_splashFragment_to_authFragment)
    }

    override fun navigateToMain() {
        navigateTo(R.id.collectionFragment)
    }

    override fun navigateToRegistration() {
        navigateTo(R.id.registrationFragment)
    }

    override fun navigateToAuthNavGraph() {
        navigateTo(R.id.auth_nav_graph)
    }

    override fun goToPreviousScreen() {
        navControllerProvider.get()?.popBackStack()
    }

    override fun navigateToFilmDetailsScreen(film: Film) {
        navigateTo(R.id.filmDetailsFragment, FilmDetailsFragment.buildArgs(film))
    }

    private fun navigateTo(actionId: Int, bundle: Bundle? = null) {
        navControllerProvider.get()
            ?.navigate(actionId, bundle)
    }
}