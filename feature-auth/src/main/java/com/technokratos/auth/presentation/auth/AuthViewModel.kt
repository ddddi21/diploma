package com.technokratos.auth.presentation.auth

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.technokratos.auth.domain.AuthInteractor
import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(
    private val router: AuthRouter,
    private val interactor: AuthInteractor
) : BaseViewModel() {

    fun onRegistrationClicked() {
        router.navigateToRegistration()
    }

    fun onEnterButtonClicked(email: String, password: String) { // пока оставлю так, дальше надо будет еще добавлять проверку введенных данных
        viewModelScope.launch {
            interactor.signIn(email, password).run {
                if (isSuccess) {
                    router.navigateToMain()
                } else {
                    Log.d("POZOR", "login failed cause ${this.exceptionOrNull()}")
                }
            }
        }
    }
}