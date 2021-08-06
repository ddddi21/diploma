package com.technokratos.auth.presentation.auth

import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.base.BaseViewModel

class AuthViewModel(
    private val router: AuthRouter
) : BaseViewModel() {

    fun onRegistrationClicked() {
        router.navigateToRegistration()
    }

    fun onEnterButtonClicked() { // пока оставлю так, дальше надо будет еще добавлять проверку введенных данных
        router.navigateToMain()
    }
}