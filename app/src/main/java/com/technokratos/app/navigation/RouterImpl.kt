package com.technokratos.app.navigation

import android.content.Context
import com.technokratos.app.R
import com.technokratos.users.presentation.details.UserFragment
import javax.inject.Inject

class RouterImpl @Inject constructor(
    private val navControllerProvider: NavControllerProvider
) : Router {

    override fun openMain(context: Context) {
        TODO("Not yet implemented")
    }

    override fun openUser(userId: Int) {
        navControllerProvider.get()?.navigate(R.id.userFragment, UserFragment.createBundle(userId))
    }

    override fun returnToUsers() {
        navControllerProvider.get()?.popBackStack()
    }

    override fun navigateToMain() {
        TODO("Not yet implemented")
    }
}