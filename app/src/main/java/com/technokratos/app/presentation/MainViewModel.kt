package com.technokratos.app.presentation

import com.example.navigation.router.MainRouter
import com.technokratos.common.base.BaseViewModel

class MainViewModel(
    private val mainRouter: MainRouter
) : BaseViewModel() {

    fun onSplashAnimationFinished() {
        mainRouter.navigateToAuthNavGraph()
    }
}