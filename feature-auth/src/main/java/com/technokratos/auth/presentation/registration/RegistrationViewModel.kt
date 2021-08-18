package com.technokratos.auth.presentation.registration

import com.technokratos.auth.domain.AuthInteractor
import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.base.BaseViewModel

class RegistrationViewModel(
    private val authRouter: AuthRouter,
    private val interactor: AuthInteractor
) : BaseViewModel() {

    fun onBackToLoginScreenClicked() {
        authRouter.goToPreviousScreen()
    }

    fun onEnterButtonClicked() { // пока оставлю так, дальше надо будет еще добавлять проверку введенных данных
        authRouter.navigateToMain()
    }
}