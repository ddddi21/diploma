package com.medicalSystem.app.presentation

import com.example.navigation.router.MainRouter
import com.medicalSystem.common.base.BaseViewModel

class MainViewModel(
    private val mainRouter: MainRouter
) : BaseViewModel() {

    fun onViewCreated() {
        mainRouter.navigateToAuthNavGraph()
    }
}