package com.technokratos.auth.presentation.registration

import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.base.BaseViewModel
import com.technokratos.common.router.NavigateBackRouter

class RegistrationViewModel(
    private val authRouter: AuthRouter, // понадобится потом, чтобы перейти после регистрации на главный экран
    private val navigateBackRouter: NavigateBackRouter
) : BaseViewModel() {

    fun onBackToLoginScreenClicked() {
        navigateBackRouter.goToPreviousScreen()
    }

    fun onEnterButtonClicked() { // пока оставлю так, дальше надо будет еще добавлять проверку введенных данных
        authRouter.navigateToMain()
    }
}