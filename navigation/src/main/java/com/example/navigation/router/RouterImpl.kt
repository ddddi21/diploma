package com.example.navigation.router

import android.content.Context
import android.os.Bundle
import com.technokratos.splash.R
import com.example.navigation.navigation.NavControllerProvider
import com.technokratos.users.presentation.details.UserFragment
import javax.inject.Inject

class RouterImpl @Inject constructor(
    private val navControllerProvider: NavControllerProvider
) : Router {

    override fun openMain(context: Context) {
        TODO("Not yet implemented")
    }

    override fun toLogin() {
        navigateTo(R.id.action_splashFragment_to_authFragment)
    }

    override fun openUser(userId: Int) {
        navigateTo(R.id.userFragment, UserFragment.createBundle(userId))
    }

    override fun returnToUsers() {
        navControllerProvider.get()?.popBackStack()
    }

    override fun navigateToMain() {
        TODO("Not yet implemented")
    }

    override fun navigateToRegistration() {
        navigateTo(R.id.registrationFragment)
    }

    override fun goToPreviousScreen() {
        navControllerProvider.get()?.popBackStack()
    }

    private fun navigateTo(actionId: Int, bundle: Bundle? = null) {
        navControllerProvider.get()
            ?.navigate(actionId, bundle)
    }
}